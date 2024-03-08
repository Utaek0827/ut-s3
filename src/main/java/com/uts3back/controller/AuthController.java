package com.uts3back.controller;

import com.uts3back.dto.UsersDTO;
import com.uts3back.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/SignIn")
    public String signIn(
            ){
        System.out.println("ㅁㄴㅇㄹㅁ");
        return "email+password";
    }

    @PostMapping("/SignUp")
    public String signUp(
            @RequestBody UsersDTO SignUpUsers
            ){

        authService.signUp(SignUpUsers);
        System.out.println(SignUpUsers);
        return "email+password";
    }

}

