package com.uts3back.controller;

import com.uts3back.dto.UserTotalServiceDTO;
import com.uts3back.service.UserTotalServiceService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserTotalServiceController {

    @Autowired
    UserTotalServiceService userTotalServiceService;

    @Operation(summary = "사용자 서비스 전체 정보 조회", description = "사용자 서비스 전체 정보")
    @GetMapping("/total-service")
    public UserTotalServiceDTO userTotalService(){

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userTotalServiceService.getUserTotalService(email);
    }


}
