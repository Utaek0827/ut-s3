package com.uts3back.controller;

import com.uts3back.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Test
    void signUp() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.post("/SignUp")
                        .contentType("application/json")
                        .content("{\"email\":\"test@example.com\"," +
                                "\"privacyCheck\":\"true\"," +
                                "\"useLevel\":\"1\"," +
                                "\"telNumber\":\"01012341231\"," +
                                " \"password\":\"password123\"}" ))
                .andExpect(status().isOk())
                .andExpect(content().string("email+password"));

    }
}