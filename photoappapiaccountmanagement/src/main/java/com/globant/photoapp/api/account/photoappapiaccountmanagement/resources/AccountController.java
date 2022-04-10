package com.globant.photoapp.api.account.photoappapiaccountmanagement.resources;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private Environment env;

    private AccountController(Environment env) {
        this.env = env;
    }
    
    @GetMapping("/status/check")
    public String status(){
        return "Working on port " + this.env.getProperty("local.server.port");
    }

}
