package com.uts3back.service;

import com.uts3back.dto.UserServiceDTO;
import com.uts3back.mapper.UserServiceMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserServiceMapper userServiceMapper;

    public UserService(UserServiceMapper userServiceMapper) {
        this.userServiceMapper = userServiceMapper;
    }

    public List<UserServiceDTO> getUserServices(String email) {
        return userServiceMapper.userServiceFind(email);
    }
}
