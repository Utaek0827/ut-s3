package com.uts3back.mapper;

import com.uts3back.dto.UsersDTO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UsersMapper {

    void insertUser(UsersDTO usersDTO);


}
