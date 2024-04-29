package com.uts3back.service;

import com.uts3back.dto.UserTotalServiceDTO;
import com.uts3back.dto.UsersServiceDTO;
import com.uts3back.mapper.UserTotalServiceMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserTotalServiceService {

    private final UserTotalServiceMapper userTotalServiceMapper;

    public UserTotalServiceService(UserTotalServiceMapper userTotalServiceMapper, UserTotalServiceMapper userTotalServiceMapper1) {
        this.userTotalServiceMapper = userTotalServiceMapper;
    }

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
