package com.globant.photoapp.api.gateway.photoappapizuulapigateway.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private Environment environment;

    public AuthorizationFilter(AuthenticationManager authenticationManager, Environment environment) {
        super(authenticationManager);
        this.environment = environment;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String authorizationHeader = req.getHeader(this.environment.getProperty("authorization.token.header.name"));

        if(Objects.isNull(authorizationHeader) || !authorizationHeader.startsWith(this.environment.getProperty("authorization.token.header.prefix"))) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
        return Optional.ofNullable(req.getHeader(this.environment.getProperty("authorization.token.header.name")))
            .map(authorizationHeader -> authorizationHeader.replace(this.environment.getProperty("authorization.token.header.prefix"), ""))
            .map(token -> Jwts.parser()
                .setSigningKey(this.environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject())
            .map(userId -> new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>()))
            .orElse(null);
    }

    
    
}
