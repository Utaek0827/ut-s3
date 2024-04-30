package com.uts3back.mapper;

import com.uts3back.dto.UserTotalServiceDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


@Mapper
public interface UserTotalServiceMapper {

    UserTotalServiceDTO userTotalServiceFind(String email);

    Integer calculateTotalImageSize(@Param("email")String email);

    int countByEmailAndValidServiceDate(@Param("email") String email, @Param("today") Date today);

    void insertUserTotalService(UserTotalServiceDTO userTotalServiceDTO);
}
