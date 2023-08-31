package com.hack.hack_server.Global.S3;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("rainbow-letter")
public class S3ImageController {

    private final S3Uploader s3Uploader;
    @GetMapping("/image")
    public ResponseEntity uploadImage(@RequestParam String dirname){
        return new ResponseEntity(s3Uploader.getPreSignedURL(dirname), HttpStatus.OK);
    }
}
