package com.uts3back.service;

import com.uts3back.dto.ImagesDTO;
import com.uts3back.mapper.ImagesMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImagesService {

    @Value("${yts3.directory}")
    private String imageUploadDirectory;

    final private ImagesMapper imagesMapper;

    public ImagesService(ImagesMapper imagesMapper) {
        this.imagesMapper = imagesMapper;
    }


    public void uploadFileService(MultipartFile file, String userServiceID) throws IOException {


        //String saveDirectory  = System.getProperty("user.dir") + "/" +imageUploadDirectory+ "/" + userServiceID;
        String saveDirectory  = System.getProperty("user.dir") + "/" +imageUploadDirectory;


        // 서비스이름으로된 폴더가 없을 경우 새로운 폴더생성
        if (!Files.exists(FileSystems.getDefault().getPath(saveDirectory))) {
            Files.createDirectories(FileSystems.getDefault().getPath(saveDirectory));
            System.out.println("Member directory created: " + saveDirectory);
        }

        // 파일저장시 중복방지를 위해 기존파일이름의 UUID값 + 확장자로 파일저장함
        // db에는 UUID값, 원본파일명, 확장자 저장됨
        String originalFilename = file.getOriginalFilename();
        int lastDotIndex = originalFilename.lastIndexOf(".");
        String extension = (lastDotIndex != -1) ? originalFilename.substring(lastDotIndex + 1) : "";

        String originalFilenameWithoutExtension = (lastDotIndex != -1) ? originalFilename.substring(0, lastDotIndex) : originalFilename;

        // UUID를 사용하여 중복 방지를 위한 파일명 생성
        String imgChanName = UUID.randomUUID().toString();

        // 파일 저장
        Path filePath = FileSystems.getDefault().getPath(saveDirectory, imgChanName+ "." + extension);
        file.transferTo(filePath);

        String imgID = String.valueOf(UUID.randomUUID());
        String imgUptime = String.valueOf(System.currentTimeMillis());
        long imgSize = file.getSize();

        ImagesDTO imagesDTO = new ImagesDTO();
        imagesDTO.setImgID(imgID);
        imagesDTO.setServiceID(userServiceID);
        imagesDTO.setImgOriName(originalFilenameWithoutExtension);
        imagesDTO.setImgChanName(imgChanName);
        imagesDTO.setImgExt(extension);
        imagesDTO.setImgUptime(imgUptime);
        imagesDTO.setImgSize(imgSize);

        imagesMapper.insertImage(imagesDTO);

        // 이미지 전송까지는 확인
        // 자고나서 할거 -> db에 저장한 이미지 저장

    }

    // 수정 메서드 추가
    public void updateImage(String imgID, ImagesDTO imagesDTO) {
        imagesMapper.updateImage(imgID, imagesDTO);
    }

    // 삭제 메서드 추가
    public void deleteImage(String imgID) {
        imagesMapper.deleteImage(imgID);
    }

    // 조회 메서드 추가
    public ImagesDTO getImage(String imgID) {
        return imagesMapper.getImage(imgID);
    }


    public Resource viewImage(String imgID) throws Exception {

        Path imagePath = Paths.get(imageUploadDirectory +"/" +imgID + ".jpg");
        Resource resource = new UrlResource(imagePath.toUri());
        System.out.println(resource);

        if(resource.exists()){
            // 존재할 경우, HTTP 응답으로 이미지를 반환함
            return resource;
        } else {
            // 존재하지 않을 경우, "Not Found" 상태 코드를 반환함
            return null;
        }

    }
}
