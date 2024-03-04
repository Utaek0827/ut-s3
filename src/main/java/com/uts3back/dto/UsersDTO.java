package com.uts3back.dto;

import lombok.Data;

@Data
public class UsersDTO {

    String email;
    String password;
    boolean privacyCheck;
    int useLevel;
    String telNumber;


}
