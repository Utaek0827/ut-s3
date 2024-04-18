package com.uts3back.mapper;

import com.uts3back.dto.ImagesDTO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ImagesMapper {

    void insertImage(ImagesDTO imagesDTO);


}
