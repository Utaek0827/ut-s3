package com.uts3back.mapper;

import com.uts3back.dto.ImagesDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ImagesMapper {

    void insertImage(ImagesDTO imagesDTO);

    // 수정 메서드 추가
    void updateImage(@Param("imgID") String imgID, @Param("imagesDTO") ImagesDTO imagesDTO);

    // 삭제 메서드 추가
    void deleteImage(@Param("imgID") String imgID);

    // 조회 메서드 추가
    ImagesDTO getImage(@Param("imgID") String imgID);

}
