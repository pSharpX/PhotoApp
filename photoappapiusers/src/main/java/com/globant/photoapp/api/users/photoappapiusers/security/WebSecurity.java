package com.globant.photoapp.api.users.photoappapiusers.security;

import javax.servlet.Filter;

import com.globant.photoapp.api.users.photoappapiusers.services.UserService;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private UserService userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Environment environment;

    public WebSecurity(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder,
    Environment environment){
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.environment = environment;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/users/**").permitAll()
        .and()
        .addFilter(getAuthenticationFilter());
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userService).passwordEncoder(this.bCryptPasswordEncoder);
    }

    private Filter getAuthenticationFilter() throws Exception {
        AuthenticationFilter filter = new AuthenticationFilter(this.userService, this.environment, this.authenticationManager());
        return filter;
    }
    
}
