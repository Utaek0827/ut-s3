package com.uts3back.controller;

import com.uts3back.dto.UsersServiceDTO;
import com.uts3back.service.UserService;
import org.apache.ibatis.annotations.Delete;
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
    public ResponseEntity<String> insertUserService(
            @RequestParam("userServiceName")String userServiceName,
            @RequestParam("userServiceInfo")String userServiceInfo){

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if(userService.insertUserService(email, userServiceName, userServiceInfo) == null){
            return ResponseEntity.ok("서비스 생성 실패");
        }
        return ResponseEntity.ok("서비스 생성 성공");
    }

    @DeleteMapping("userService")
    public ResponseEntity<String> deleteUserService(
            @RequestParam("userServiceID")String userServiceID
    ){
        userService.deleteUserService(userServiceID);
        return ResponseEntity.ok("서비스 삭제 성공");
    }

    @PutMapping("userService")
    public ResponseEntity<String> updateUserService(
            @RequestParam("userServiceID")String userServiceID
    ){
        userService.deleteUserService(userServiceID);
        return ResponseEntity.ok("서비스 삭제 성공");
    }


}
