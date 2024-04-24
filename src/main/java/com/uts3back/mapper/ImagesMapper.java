package com.uts3back.mapper;

import com.uts3back.dto.ImagesDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


@Mapper
public interface ImagesMapper {

    void insertImage(ImagesDTO imagesDTO);

    // 수정 메서드 추가
    void updateImage(ImagesDTO imagesDTO);

    // 삭제 메서드 추가
    void deleteImage(@Param("imgID") String imgID);

    // 조회 메서드 추가
    ImagesDTO getImage(@Param("imgID") String imgID);

    List<ImagesDTO> getImagesByServiceID(@Param("serviceID") String serviceID, RowBounds rowBounds);


}
