package com.uts3back.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @GetMapping("{sign-in}")
    public String signIn(){
        System.out.println("api test");
        return "hello world";
    }


}
