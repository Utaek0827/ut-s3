package com.uts3back.service;

import com.uts3back.dto.CustomUserDetails;
import com.uts3back.dto.UsersDTO;
import com.uts3back.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UsersMapper usersMapper;

    public AuthService(BCryptPasswordEncoder bCryptPasswordEncoder, UsersMapper usersMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.usersMapper = usersMapper;
    }

    public void signUp(UsersDTO usersDTO) {
        usersDTO.setPassword(bCryptPasswordEncoder.encode(usersDTO.getPassword()));
        usersMapper.insertUser(usersDTO);
    }

    public UsersDTO userEmailCheck(String UserEmail){
        return usersMapper.userEmailCheck(UserEmail);
    }

    public boolean userPwCheck(UsersDTO UsersDTO, String password){
        //System.out.println("패턴매칭" + UsersDTO + "password:" + password + "매칭:"+ bCryptPasswordEncoder.matches(password, UsersDTO.getPassword()));
        //System.out.println(UsersDTO.getPassword());

        return bCryptPasswordEncoder.matches(password, UsersDTO.getPassword());
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsersDTO usersDTO = userEmailCheck(username);
        if(usersDTO != null){

            return  new CustomUserDetails(usersDTO);
        }

        return null;
    }
}
