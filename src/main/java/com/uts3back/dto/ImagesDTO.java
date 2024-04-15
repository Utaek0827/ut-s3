package com.uts3back.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ImagesDTO {

    String imgID;
    String serviceID;
    String imgOriName;
    String imgChanName;
    String imgExt;
    Date imgUptime;
    int imgSize;

}
