package com.hack.hack_server.Dalle.Controller;

import com.hack.hack_server.Authentication.PrincipalDetails;
import com.hack.hack_server.ChatGpt.Dto.DalleAnswerResponseDto;
import com.hack.hack_server.ChatGpt.Dto.QuestionRequestDto;
import com.hack.hack_server.ChatGpt.Service.ChatGptService;
import com.hack.hack_server.ChatGpt.Service.MockMultipartFile;
import com.hack.hack_server.Dalle.Service.AIService;
import com.hack.hack_server.Global.S3.S3Uploader;
import com.hack.hack_server.Papago.NaverTransService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.hack.hack_server.ChatGpt.Dto.ChatGptAnswerResponseDto;
import org.springframework.web.multipart.MultipartFile;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/journal")
@RequiredArgsConstructor
public class ImageGeneratorController {

    private final AIService aiService;
    private final NaverTransService naverTransService;
    private final ChatGptService chatGptService;
    private final S3Uploader s3Uploader;


    //메인 기능: 그림일기(글+그림) 생성 후 s3에 저장
    @PostMapping("/{pet_id}/image")
    public ResponseEntity<?> generateImage(@PathVariable Long pet_id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        DalleAnswerResponseDto dalleAnswerResponseDto = chatGptService.generateJournal(pet_id, principalDetails); //chatGPT: 그림일기 생성 (글)
        Long jourId = dalleAnswerResponseDto.getJourId();
        String koJournal = dalleAnswerResponseDto.getAnswer();

        String shortKoJour = chatGptService.shortJorunal(koJournal).getAnswer(); //DALL-E에게 전달을 위한 1문장 요약

        String img = aiService.generatePicture(naverTransService.getTransSentence(shortKoJour)); // papago를 거쳐 DALL-E를 통해 반환된 b64_json String
        chatGptService.generateImage(pet_id, principalDetails, img, jourId); //DB에 그림일기 저장!

        byte[] image = Base64.decodeBase64(img); //b64 string -> byte[]


        int totalCnt = 1024;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(totalCnt)) {
            int offset = 0;
            while (offset < image.length) {
                int chunkSize = Math.min(totalCnt, image.length - offset);

                byte[] byteArray = new byte[chunkSize];
                System.arraycopy(image, offset, byteArray, 0, chunkSize);

                byteArrayOutputStream.write(byteArray);
                byteArrayOutputStream.flush();

                offset += chunkSize;
            }

            // Convert the ByteArrayOutputStream to ByteArrayInputStream
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

            // Create an MultipartFile object
            MultipartFile multipartFile = new MockMultipartFile("그림 일기", byteArrayInputStream.readAllBytes());

            s3Uploader.saveFile(multipartFile); //s3에 멀티파트 파일로 직접 업로드! => ok.

        } catch (IOException e) {
        }

        return new ResponseEntity<>(img, HttpStatus.OK);
    }



    //chatGPT & papago api 테스트
    @PostMapping("/{pet_id}")
    public String sendQuestion(@PathVariable Long pet_id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        //requestDto 내용으로 "일기를 써줘."가 들어가고, 그 일기 내용이 아래의 Prompt로 들어가야함!
        String koJournal = chatGptService.generateJournal(pet_id, principalDetails).getAnswer();
        String shortKoJour = chatGptService.shortJorunal(koJournal).getAnswer(); //DALL-E에게 전달을 위한 1문장 요약
//        String enJournal = naverTransService.getTransSentence(chatGptService.generateJournal(pet_id, principalDetails).getAnswer());
        return shortKoJour;
    }



    //DALL-E api 테스트
    @PostMapping("/image")
    public ResponseEntity<?> generateImage(@RequestBody String prompt) {
        return new ResponseEntity<>(aiService.generatePicture(prompt), HttpStatus.OK);
    }


}