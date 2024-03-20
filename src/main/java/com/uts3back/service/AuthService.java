package com.uts3back.service;

import com.uts3back.dto.UsersDTO;
import com.uts3back.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    private UsersMapper usersMapper;

    public AuthService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void signUp(UsersDTO usersDTO) {
        usersDTO.setPassword(bCryptPasswordEncoder.encode(usersDTO.getPassword()));
        usersMapper.insertUser(usersDTO);
    }

    public UsersDTO userEmailCheck(String UserEmail){

        return usersMapper.userEmailCheck(UserEmail);
    }

    public boolean userPwCheck(UsersDTO UsersDTO, String password){
        System.out.println("패턴매칭" + UsersDTO + "password:" + password + "매칭:"+ bCryptPasswordEncoder.matches(password, UsersDTO.getPassword()));
        System.out.println(UsersDTO.getPassword());

        return bCryptPasswordEncoder.matches(password, UsersDTO.getPassword());
    }



}
