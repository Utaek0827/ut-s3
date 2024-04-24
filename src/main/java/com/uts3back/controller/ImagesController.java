package com.uts3back.controller;

import com.uts3back.dto.ImagesDTO;
import com.uts3back.service.ImagesService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
public class ImagesController {

    @Autowired
    ImagesService imagesService;

    @Operation(summary = "이미지 업로드", description = "사용자가 선택한 이미지 업로드")
    @PostMapping(value = "/upload" , consumes = {"multipart/form-data"})
    public ResponseEntity<String> imageUpload(
            @RequestPart("file") MultipartFile file,
            @RequestPart("userServiceID") String userServiceID) throws Exception {

        imagesService.uploadFileService(file, userServiceID);
        return ResponseEntity.ok("이미지 업로드 성공");
    }
    // 수정 컨트롤러 추가
    @Operation(summary = "이미지 수정", description = "이미지 정보 수정")
    @PutMapping(value = "/{imgID}", consumes = {"multipart/form-data"})
    public ResponseEntity<String> updateImage(
            @PathVariable("imgID") String imgID,
            @RequestPart("file") MultipartFile file) {
        imagesService.updateImage(imgID, file);
        return ResponseEntity.ok("이미지 수정 완료");
    }
    // 삭제 컨트롤러 추가
    @Operation(summary = "이미지 삭제", description = "이미지 삭제")
    @DeleteMapping("/{imgID}")
    public ResponseEntity<String> deleteImage(@PathVariable("imgID") String imgID) {
        if(imagesService.deleteImage(imgID)){
            return ResponseEntity.ok("이미지 삭제 완료");
        }else{
            return ResponseEntity.status(404).body(null);
        }
    }

    // 조회 컨트롤러 추가
    @Operation(summary = "이미지 조회", description = "이미지 정보 조회")
    @GetMapping("/{imgID}")
    public ResponseEntity<ImagesDTO> getImage(@PathVariable("imgID") String imgID) {
        ImagesDTO image = imagesService.getImage(imgID);
        return ResponseEntity.ok(image);
    }

    @Operation(summary = "이미지 리스트 조회", description = "서비스 ID를 이용한 이미지 정보 리스트 조회")
    @GetMapping("/list/{serviceID}")
    public ResponseEntity<List<ImagesDTO>> getImagesByServiceID(@PathVariable("serviceID") String serviceID,
                                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                                @RequestParam(value = "size", defaultValue = "10") int size) {
        List<ImagesDTO> images = imagesService.getImagesByServiceID(serviceID, page, size);
        return ResponseEntity.ok(images);
    }







}
