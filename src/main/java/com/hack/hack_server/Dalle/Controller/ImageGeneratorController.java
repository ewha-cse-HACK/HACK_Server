package com.hack.hack_server.Dalle.Controller;

import com.hack.hack_server.Dalle.Service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageGeneratorController {

    private final AIService aiService;


    @PostMapping("/generate")
    public ResponseEntity<?> generateImage(@RequestBody String prompt) {
        return new ResponseEntity<>(aiService.generatePicture(prompt), HttpStatus.OK);
    }

}