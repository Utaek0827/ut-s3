package com.uts3back.service;

import com.uts3back.dto.UsersDTO;
import com.uts3back.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsersMapper usersMapper;

    public void signUp(UsersDTO usersDTO) {
        usersMapper.insertUser(usersDTO);
    }

}
