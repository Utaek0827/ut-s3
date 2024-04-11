package com.uts3back.mapper;

import com.uts3back.dto.UserTotalServiceDTO;
import com.uts3back.dto.UsersServiceDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersServiceMapper {

    List<UsersServiceDTO> usersServiceListFind(String email);

    UsersServiceDTO usersServiceFind(String userServiceID);

    void insertUsersService(UsersServiceDTO insertUSDTO);
}
