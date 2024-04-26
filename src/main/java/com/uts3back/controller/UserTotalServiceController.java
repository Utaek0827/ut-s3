package com.uts3back.controller;

import com.uts3back.dto.UserTotalServiceDTO;
import com.uts3back.service.UserTotalServiceService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/total-service")
public class UserTotalServiceController {

    @Autowired
    UserTotalServiceService userTotalServiceService;

    @Operation(summary = "사용자 서비스 전체 정보 조회", description = "사용자 서비스 전체 정보")
    @GetMapping("/{email}")
    public UserTotalServiceDTO userTotalService(@PathVariable("email")String email){

        System.out.println(userTotalServiceService.getUserTotalService(email));
        return userTotalServiceService.getUserTotalService(email);
    }


}
