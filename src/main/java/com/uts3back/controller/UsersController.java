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

    @GetMapping("/dashboard")
    public List<UsersServiceDTO> Dashboard(){

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        System.out.println(email);
        List<UsersServiceDTO> usersServices = userService.getUsersServicesList(email);

        System.out.println(usersServices);
        for (UsersServiceDTO userService : usersServices) {
            System.out.println(userService); // 또는 userService의 필드를 개별적으로 출력
        }
        return usersServices;
    }






}
