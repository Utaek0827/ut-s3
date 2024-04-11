package com.uts3back.service;

import com.uts3back.dto.UserTotalServiceDTO;
import com.uts3back.dto.UsersServiceDTO;
import com.uts3back.mapper.UserTotalServiceMapper;
import com.uts3back.mapper.UsersMapper;
import com.uts3back.mapper.UsersServiceMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Value("${JWT_KEY}")
    String key;

    private final UserTotalServiceMapper userTotalServiceMapper;
    private final UsersServiceMapper usersServiceMapper;

    public UserService(UserTotalServiceMapper userTotalServiceMapper, UsersServiceMapper usersServiceMapper) {
        this.userTotalServiceMapper = userTotalServiceMapper;
        this.usersServiceMapper = usersServiceMapper;
    }

    public List<UsersServiceDTO> getUsersServicesList(String email) {
        return usersServiceMapper.usersServiceListFind(email);
    }

    public UsersServiceDTO getUsersService(String userServiceID) {
        return usersServiceMapper.usersServiceFind(userServiceID);
    }


    public void insertUserService(String email, String userServiceName, String userServiceInfo) {

        System.out.println("key: "+key);
        String userServiceID = email + '_' + userServiceName.hashCode() + "_" + UUID.randomUUID().toString();
        String userServiceKEY = email + '_' + userServiceName.hashCode() + "_" + key + "_" + UUID.randomUUID().toString();



        System.out.println("userServiceID:"+userServiceID);
        System.out.println("userServiceKEY:"+userServiceKEY);

        UsersServiceDTO insertUSDTO = new UsersServiceDTO();
        insertUSDTO.setUserServiceID(userServiceID);
        insertUSDTO.setUserServiceKey(userServiceKEY);
        insertUSDTO.setUserServiceName(userServiceName);
        insertUSDTO.setUserServiceInfo(userServiceInfo);
        insertUSDTO.setEmail(email);

        usersServiceMapper.insertUsersService(insertUSDTO);


    }
}
