package com.uts3back.controller;

import com.uts3back.dto.UsersServiceDTO;
import com.uts3back.service.UserService;
import com.uts3back.service.UserTotalServiceService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/users")
public class UserServiceController {

    @Autowired
    UserService userService;

    @Autowired
    UserTotalServiceService userTotalServiceService;

    @Operation(summary = "사용자 서비스 정보 조회", description = "사용자 서비스 ID를 통해 해당 서비스 정보를 조회합니다.")
    @GetMapping("/{user-service-id}")
    public UsersServiceDTO userService(@PathVariable("user-service-id")String userServiceID){
        return userService.getUsersService(userServiceID);
    }

    @Operation(summary = "사용자 서비스 생성", description = "사용자 서비스 생성 서비스 이름과 서비스 설명작성")
    @PostMapping("user-service")
    public ResponseEntity<String> insertUserService(
            @RequestParam("userServiceName")String userServiceName,
            @RequestParam("userServiceInfo")String userServiceInfo){

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Date today = new Date();
        if(0 == userTotalServiceService.CheckUserLicense(email, today)){
            return ResponseEntity.ok("유효기간이 올바르지 않습니다.");
        }
        return ResponseEntity.ok("서비스 생성 성공");
    }

    @Operation(summary = "사용자 서비스 삭제", description = "사용자 서비스 ID를 통해 해당 서비스 정보를 삭제")
    @DeleteMapping("/{user-service-id}")
    public ResponseEntity<String> deleteUserService(
            @PathVariable("user-service-id")String userServiceID
    ){
        userService.deleteUserService(userServiceID);
        return ResponseEntity.ok("서비스 삭제 성공");
    }

    @Operation(summary = "사용자 서비스 수정", description = "사용자 서비스 ID를 통해 서비스에 대한 설명수정")
    @PutMapping("user-service")
    public ResponseEntity<String> updateUserService(
            @RequestParam("userServiceID")String userServiceID,
            @RequestParam("userServiceInfo")String userServiceInfo

    ){
        userService.updateUserService(userServiceID,userServiceInfo);
        return ResponseEntity.ok("서비스 수정 성공");
    }


}
