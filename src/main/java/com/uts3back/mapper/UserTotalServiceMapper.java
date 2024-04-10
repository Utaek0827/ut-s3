package com.uts3back.mapper;

import com.uts3back.dto.UserTotalServiceDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserTotalServiceMapper {

    List<UserTotalServiceDTO> userTotalServiceFind(String email);

}
