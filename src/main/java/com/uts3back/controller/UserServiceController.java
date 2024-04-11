package com.uts3back.controller;

import com.uts3back.dto.UsersServiceDTO;
import com.uts3back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserServiceController {

    @Autowired
    UserService userService;

    @GetMapping("userService")
    public UsersServiceDTO userService(@RequestParam("userServiceID")String userServiceID){
        return userService.getUsersService(userServiceID);
    }

    @PostMapping("userService")
    public ResponseEntity<String> userService(
            @RequestParam("userServiceName")String userServiceName,
            @RequestParam("userServiceInfo")String userServiceInfo){

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.insertUserService(email, userServiceName, userServiceInfo);

        return ResponseEntity.ok("서비스 생성 성공");
    }



}
