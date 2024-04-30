package com.uts3back.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class UserTotalServiceDTO {

    String usID;
    String email;
    long userTotalCap;
    long userUsageCap;
    LocalDate userStartDate;
    LocalDate userEndDate;


}
