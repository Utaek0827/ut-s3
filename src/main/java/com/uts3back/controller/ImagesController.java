package com.uts3back.controller;

import com.uts3back.dto.ImagesDTO;
import com.uts3back.service.ImagesService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


        return ResponseEntity.ok("서비스 생성 성공");
    }
    // 수정 컨트롤러 추가
    @Operation(summary = "이미지 수정", description = "이미지 정보 수정")
    @PutMapping("/{imgID}")
    public ResponseEntity<String> updateImage(
            @PathVariable("imgID") String imgID,
            @RequestBody ImagesDTO imagesDTO) {
        imagesService.updateImage(imgID, imagesDTO);
        return ResponseEntity.ok("이미지 수정 완료");
    }
    // 삭제 컨트롤러 추가
    @Operation(summary = "이미지 삭제", description = "이미지 삭제")
    @DeleteMapping("/{imgID}")
    public ResponseEntity<String> deleteImage(@PathVariable("imgID") String imgID) {
        imagesService.deleteImage(imgID);
        return ResponseEntity.ok("이미지 삭제 완료");
    }

    // 조회 컨트롤러 추가
    @Operation(summary = "이미지 조회", description = "이미지 정보 조회")
    @GetMapping("/{imgID}")
    public ResponseEntity<ImagesDTO> getImage(@PathVariable("imgID") String imgID) {
        ImagesDTO image = imagesService.getImage(imgID);
        return ResponseEntity.ok(image);
    }






}
