package com.uts3back.service;

import com.uts3back.mapper.UsersMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
class AuthServiceTest {


    @Autowired
    AuthService authService;
    @Autowired
    UsersMapper usersMapper;

    @DisplayName("회원가입 성공")
    @Test
    void signUp() {
    }
}