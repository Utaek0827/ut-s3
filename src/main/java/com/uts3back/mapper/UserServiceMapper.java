package com.uts3back.mapper;

import com.uts3back.dto.UserServiceDTO;
import com.uts3back.dto.UsersDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserServiceMapper {

    List<UserServiceDTO> userServiceFind(String email);

}
