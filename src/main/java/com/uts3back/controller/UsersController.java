package com.uts3back.controller;

import com.uts3back.dto.UserTotalServiceDTO;
import com.uts3back.dto.UsersServiceDTO;
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
    public List<UsersServiceDTO> Dashboard(String msg){

        String name = SecurityContextHolder.getContext().getAuthentication().getName();


        System.out.println(name);
        List<UsersServiceDTO> usersServices = userService.getUsersServices(name);

        System.out.println(usersServices);
        for (UsersServiceDTO userService : usersServices) {
            System.out.println(userService); // 또는 userService의 필드를 개별적으로 출력
        }
        return usersServices;
    }


}
