package com.uts3back.controller;

import com.uts3back.dto.ImagesDTO;
import com.uts3back.service.ImagesService;
import com.uts3back.service.UserTotalServiceService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/images")
public class ImagesController {

    @Autowired
    ImagesService imagesService;

    @Autowired
    UserTotalServiceService userTotalServiceService;

    @Operation(summary = "이미지 업로드", description = "사용자가 선택한 이미지 업로드")
    @PostMapping(value = "/upload" , consumes = {"multipart/form-data"})
    public ResponseEntity<String> imageUpload(
            @RequestPart("file") MultipartFile file,
            @RequestPart("user-service-id") String userServiceID) throws Exception {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Date today = new Date();
        if(0 == userTotalServiceService.CheckUserLicense(email, today)){
            return ResponseEntity.ok("유효기간이 올바르지 않습니다.");
        }

        Map res = new HashMap<>();

        res.put("kind",0);
        res.put("email",email);
        res.put("imgSize",file.getSize());

        boolean imgSizeRes = userTotalServiceService.correctionImgSize(res);

        if(imgSizeRes){
            imagesService.uploadFileService(file, userServiceID);
            return ResponseEntity.ok("이미지 업로드 성공");
        }else{
            return ResponseEntity.ok("이미지 크기를 확인하세요");
        }
    }
    // 수정 컨트롤러 추가
    @Operation(summary = "이미지 수정", description = "이미지 정보 수정")
    @PutMapping(value = "/{img-id}", consumes = {"multipart/form-data"})
    public ResponseEntity<String> updateImage(
            @PathVariable("img-id") String imgID,
            @RequestPart("file") MultipartFile file) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        // 요청한 사용자가 이미지의 소유자인지 확인합니다.
        if (!imagesService.findEmailByImgID(imgID).equals(email)) {
            return ResponseEntity.status(403).body("이미지 수정 권한이 없습니다.");
        }

        Map res = new HashMap<>();
        res.put("kind",1);
        res.put("email",email);
        res.put("imgSize",file.getSize());
        res.put("imgOriSize",imagesService.getImage(imgID).getImgSize());

        boolean imgSizeRes = userTotalServiceService.correctionImgSize(res);

        if(imgSizeRes){
            imagesService.updateImage(imgID, file);
            return ResponseEntity.ok("이미지 수정 완료");
        }else{
            return ResponseEntity.ok("이미지 크기를 확인하세요");
        }
        
    }
    // 삭제 컨트롤러 추가
    @Operation(summary = "이미지 삭제", description = "이미지 삭제")
    @DeleteMapping("/{img-id}")
    public ResponseEntity<String> deleteImage(@PathVariable("img-id") String imgID) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // 요청한 사용자가 이미지의 소유자인지 확인합니다.
        if (!imagesService.findEmailByImgID(imgID).equals(email)) {
            return ResponseEntity.status(403).body("이미지 삭제 권한이 없습니다.");
        }

        Map res = new HashMap<>();
        res.put("kind",2);
        res.put("email",email);
        res.put("imgOriSize",imagesService.getImage(imgID).getImgSize());

        userTotalServiceService.correctionImgSize(res);

        if(imagesService.deleteImage(imgID)){
            return ResponseEntity.ok("이미지 삭제 완료");
        }else{
            return ResponseEntity.status(404).body("이미지가 존재하지 않음");
        }
    }

    // 조회 컨트롤러 추가
    @Operation(summary = "이미지 조회", description = "이미지 정보 조회")
    @GetMapping("/{img-id}")
    public ResponseEntity getImage(@PathVariable("img-id") String imgID) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        // 요청한 사용자가 이미지의 소유자인지 확인합니다.
        if (!imagesService.findEmailByImgID(imgID).equals(email)) {
            return ResponseEntity.status(403).body("이미지 조회 권한이 없습니다.");
        }

        ImagesDTO imagesDTO = imagesService.getImage(imgID);
        return ResponseEntity.ok(imagesDTO);
    }

    @Operation(summary = "이미지 리스트 조회", description = "서비스 ID를 이용한 이미지 정보 리스트 조회")
    @GetMapping("/list/{service-id}")
    public ResponseEntity<List<ImagesDTO>> getImagesByServiceID(@PathVariable("service-id") String serviceID,
                                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                                @RequestParam(value = "size", defaultValue = "10") int size) {
        List<ImagesDTO> images = imagesService.getImagesByServiceID(serviceID, page, size);
        return ResponseEntity.ok(images);
    }







}
