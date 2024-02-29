package com.uts3back.auth;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/SignIn")
    public String signIn(
            ){
        System.out.println("ㅁㄴㅇㄹㅁ");
        return "email+password";
    }
}

