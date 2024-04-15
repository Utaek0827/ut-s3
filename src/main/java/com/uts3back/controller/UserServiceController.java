package com.uts3back.controller;

import com.uts3back.dto.UsersServiceDTO;
import com.uts3back.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "사용자 서비스 정보 조회", description = "사용자 서비스 ID를 통해 해당 서비스 정보를 조회합니다.")
    @GetMapping("userService")
    public UsersServiceDTO userService(@RequestParam("userServiceID")String userServiceID){
        return userService.getUsersService(userServiceID);
    }

    @Operation(summary = "사용자 서비스 생성", description = "사용자 서비스 생성 서비스 이름과 서비스 설명작성")
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

    @Operation(summary = "사용자 서비스 삭제", description = "사용자 서비스 ID를 통해 해당 서비스 정보를 삭제")
    @DeleteMapping("userService")
    public ResponseEntity<String> deleteUserService(
            @RequestParam("userServiceID")String userServiceID
    ){
        userService.deleteUserService(userServiceID);
        return ResponseEntity.ok("서비스 삭제 성공");
    }

    @Operation(summary = "사용자 서비스 수정", description = "사용자 서비스 ID를 통해 서비스에 대한 설명수정")
    @PutMapping("userService")
    public ResponseEntity<String> updateUserService(
            @RequestParam("userServiceID")String userServiceID,
            @RequestParam("userServiceInfo")String userServiceInfo

    ){
        userService.updateUserService(userServiceID,userServiceInfo);
        return ResponseEntity.ok("서비스 수정 성공");
    }


}
