package com.uts3back.controller;

import com.uts3back.dto.UsersDTO;
import com.uts3back.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(
            @RequestBody UsersDTO SignInUsers
            ){

        UsersDTO logInUsers = authService.userEmailCheck(SignInUsers.getEmail());

        if(logInUsers != null && authService.userPwCheck(logInUsers, SignInUsers.getPassword())){
            return ResponseEntity.ok("로그인 성공");
        }
        System.out.println(logInUsers);

        return ResponseEntity.ok("로그인 실패");
    }

    @PostMapping("/signUp")
    public String signUp(
            @RequestBody UsersDTO SignUpUsers
            ){

        authService.signUp(SignUpUsers);
        System.out.println(SignUpUsers);
        return "email+password";
    }

    @GetMapping("/ttt")
    public String ttt(String msg){

        System.out.println("관리자 확인");
        return msg;
    }



}

