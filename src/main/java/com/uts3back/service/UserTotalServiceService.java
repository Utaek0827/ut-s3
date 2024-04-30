package com.uts3back.service;

import com.uts3back.dto.UserTotalServiceDTO;
import com.uts3back.dto.UsersServiceDTO;
import com.uts3back.mapper.UserTotalServiceMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Service
public class UserTotalServiceService {

    private final UserTotalServiceMapper userTotalServiceMapper;

    public UserTotalServiceService(UserTotalServiceMapper userTotalServiceMapper, UserTotalServiceMapper userTotalServiceMapper1) {
        this.userTotalServiceMapper = userTotalServiceMapper;
    }

    // 회원가입 시 기본 1기가 + 1년으로 생성
    public void insertUserTotalService(String email){
        String usID = String.valueOf(UUID.randomUUID());
        LocalDate today = LocalDate.now();
        LocalDate nextYear = today.plusYears(1);

        UserTotalServiceDTO userTotalServiceDTO = new UserTotalServiceDTO();
        userTotalServiceDTO.setUsID(usID);
        userTotalServiceDTO.setEmail(email);
        userTotalServiceDTO.setUserTotalCap(1073741824); // 기본 1기가
        userTotalServiceDTO.setUserUsageCap(0);

        userTotalServiceDTO.setUserStartDate(today);
        userTotalServiceDTO.setUserEndDate(nextYear);

        userTotalServiceMapper.insertUserTotalService(userTotalServiceDTO);

    }

    // 토탈서비스 날짜 체크
    public int CheckUserLicense(String email, Date today){
        return userTotalServiceMapper.countByEmailAndValidServiceDate(email, today);
    }

    public UserTotalServiceDTO getUserTotalService(String email) {
        UserTotalServiceDTO userTotalServiceDTO = userTotalServiceMapper.userTotalServiceFind(email);
        Integer size = userTotalServiceMapper.calculateTotalImageSize(email);
        userTotalServiceDTO.setUserUsageCap(size);

        //토탈서비스 생성

        return userTotalServiceDTO;

    }
}
