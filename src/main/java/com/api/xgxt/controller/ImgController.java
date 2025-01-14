package com.api.xgxt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.xgxt.service.ImgService;



@RestController	
@RequestMapping(path="/pic")

public class ImgController {
    @Autowired
    private ImgService imgService;

    @PostMapping("/uploadpic")
    public ResponseEntity<String> uploadpic(@RequestBody MultipartFile file, @RequestParam Long id) {
        return imgService.uploadImg(id, file);
    }
    
    @GetMapping("/getImgById")
    public ResponseEntity<String> getMethodName(@RequestParam Long id) {
        return imgService.getImgById(id);
    }
    
}
