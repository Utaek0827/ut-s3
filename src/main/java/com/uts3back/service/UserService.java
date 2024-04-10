package com.uts3back.service;

import com.uts3back.dto.UserTotalServiceDTO;
import com.uts3back.dto.UsersServiceDTO;
import com.uts3back.mapper.UserTotalServiceMapper;
import com.uts3back.mapper.UsersMapper;
import com.uts3back.mapper.UsersServiceMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserTotalServiceMapper userTotalServiceMapper;
    private final UsersServiceMapper usersServiceMapper;

    public UserService(UserTotalServiceMapper userTotalServiceMapper, UsersServiceMapper usersServiceMapper) {
        this.userTotalServiceMapper = userTotalServiceMapper;
        this.usersServiceMapper = usersServiceMapper;

    }

    public List<UsersServiceDTO> getUsersServices(String email) {
        return usersServiceMapper.usersServiceFind(email);
    }
}
