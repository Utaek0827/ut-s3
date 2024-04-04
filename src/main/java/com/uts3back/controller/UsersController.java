package com.uts3back.controller;

import com.uts3back.dto.UserServiceDTO;
import com.uts3back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    @Autowired
    UserService userService;

    @GetMapping("/Dashboard")
    public List<UserServiceDTO> Dashboard(String msg){

        String name = SecurityContextHolder.getContext().getAuthentication().getName();



        List<UserServiceDTO> userServices = userService.getUserServices(name);

        for (UserServiceDTO userService : userServices) {
            System.out.println(userService); // 또는 userService의 필드를 개별적으로 출력
        }
        return userServices;
    }


}
