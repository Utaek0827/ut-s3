package com.uts3back.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    @GetMapping("/ttt")
    public String ttt(String msg){

        System.out.println("토큰 확인");
        return msg;
    }


}
