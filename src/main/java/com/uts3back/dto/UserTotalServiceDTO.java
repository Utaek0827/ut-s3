package com.uts3back.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserTotalServiceDTO {

    String usID;
    String email;
    int userTotalCap;
    int userUsageCap;
    Date userStartDate;
    Date userEndDate;


}
