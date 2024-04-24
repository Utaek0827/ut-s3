package com.uts3back.service;

import com.uts3back.dto.ImagesDTO;
import com.uts3back.mapper.ImagesMapper;
import org.apache.ibatis.session.RowBounds;
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
import java.util.List;
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
    public void updateImage(String imgID, MultipartFile file) {

        System.out.println(imgID +  file.getOriginalFilename());
        ImagesDTO imagesDTO = getImage(imgID); // 이미지 정보를 가져옴
        String imgUptime = String.valueOf(System.currentTimeMillis());
        long imgSize = file.getSize();

        Path imagePath = Paths.get(imageUploadDirectory +"/" + imagesDTO.getImgChanName() + "." + imagesDTO.getImgExt());

        try {
            // 기존파일 삭제
            Files.deleteIfExists(imagePath);
            // 수정파일 저장
            Path filePath = FileSystems.getDefault().getPath(imageUploadDirectory +"/", imagesDTO.getImgChanName()+ "." + imagesDTO.getImgExt());
            file.transferTo(filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
        imagesDTO.setImgSize(imgSize);
        imagesDTO.setImgUptime(imgUptime);

        // 수정파일 원본이름 저장
        String originalFilename = file.getOriginalFilename();
        int lastDotIndex = originalFilename.lastIndexOf(".");
        String originalFilenameWithoutExtension = (lastDotIndex != -1) ? originalFilename.substring(0, lastDotIndex) : originalFilename;
        imagesDTO.setImgOriName(originalFilenameWithoutExtension);

        System.out.println(imagesDTO);

        imagesMapper.updateImage(imagesDTO);
    }

    // 삭제 메서드 추가
    public boolean deleteImage(String imgID) {

        ImagesDTO imagesDTO = getImage(imgID); // 이미지 정보를 가져옴

        // 이미지 파일 경로 생성
        Path imagePath = Paths.get(imageUploadDirectory +"/" + imagesDTO.getImgChanName() + "." + imagesDTO.getImgExt());

        // 파일 삭제 시도
        try {
            Files.deleteIfExists(imagePath);
            imagesMapper.deleteImage(imgID);
            return true; // 파일 삭제 성공
        } catch (IOException e) {
            e.printStackTrace();
            return false; // 파일 삭제 실패
        }
    }

    // 조회 메서드 추가
    public ImagesDTO getImage(String imgID) {
        return imagesMapper.getImage(imgID);
    }

    public List<ImagesDTO> getImagesByServiceID(String serviceID, int page, int size) {
        int offset = page * size;
        RowBounds rowBounds = new RowBounds(offset, size);
        return imagesMapper.getImagesByServiceID(serviceID, rowBounds);
    }



    public Resource viewImage(String imgID) throws Exception {

        ImagesDTO imagesDTO = getImage(imgID);


        Path imagePath = Paths.get(imageUploadDirectory +"/" +imagesDTO.getImgChanName() + "." + imagesDTO.getImgExt());
        Resource resource = new UrlResource(imagePath.toUri());
        System.out.println(resource);

        if(resource.exists()){
            return resource;
        } else {
            return null;
        }

    }
}
