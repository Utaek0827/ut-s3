package com.uts3back.controller;

import com.uts3back.dto.ImagesDTO;
import com.uts3back.service.ImagesService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class ViewImgController {

    @Autowired
    ImagesService imagesService;
    @Operation(summary = "이미지 보기", description = "이미지 보기")
    @GetMapping("/{imgID}")
    public ResponseEntity viewImg(@PathVariable String imgID) throws Exception {
        Resource resource = imagesService.viewImage(imgID);

        if(resource != null){
            return ResponseEntity.ok()
                    // 이미지의 MIME 타입을 명시함
                    .header("Content-Type", "image/jpg")
                    .body(resource);
        } else {
            // 존재하지 않을 경우, "Not Found" 상태 코드를 반환함
            return ResponseEntity.status(404).body(null);
        }

    }


}
