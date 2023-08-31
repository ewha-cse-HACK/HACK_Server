package com.hack.hack_server.Dalle.Controller;

import com.hack.hack_server.Authentication.PrincipalDetails;
import com.hack.hack_server.ChatGpt.Dto.QuestionRequestDto;
import com.hack.hack_server.ChatGpt.Service.ChatGptService;
import com.hack.hack_server.Dalle.Service.AIService;
import com.hack.hack_server.Papago.NaverTransService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.hack.hack_server.ChatGpt.Dto.ChatGptAnswerResponseDto;

@RestController
@RequestMapping("/journal")
@RequiredArgsConstructor
public class ImageGeneratorController {

    private final AIService aiService;
    private final NaverTransService naverTransService;
    private final ChatGptService chatGptService;


//    @PostMapping("/{pet_id}")
//    public ChatGptAnswerResponseDto sendQuestion(@PathVariable Long pet_id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        ////requestDto 내용으로 "일기를 써줘."가 들어가고, 그 일기 내용이 아래의 Prompt로 들어가야함!
//        return chatGptService.generateJournal(pet_id, principalDetails);
//    }

    @PostMapping("/{pet_id}")
    public String sendQuestion(@PathVariable Long pet_id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        //requestDto 내용으로 "일기를 써줘."가 들어가고, 그 일기 내용이 아래의 Prompt로 들어가야함!
        String koJournal = chatGptService.generateJournal(pet_id, principalDetails).getAnswer();
        String shortKoJour = chatGptService.shortJorunal(koJournal).getAnswer(); //DALL-E에게 전달을 위한 1문장 요약
//        String enJournal = naverTransService.getTransSentence(chatGptService.generateJournal(pet_id, principalDetails).getAnswer());
        return shortKoJour;
    }


    //[기능: 일기 훔쳐보기]
//    @PostMapping("/image")
//    public ResponseEntity<?> generateImage(@RequestBody String prompt) {
////        System.out.print(naverTransService.getTransSentence(prompt)); //Papago API 정상 작동 확인
//        return new ResponseEntity<>(aiService.generatePicture(naverTransService.getTransSentence(prompt)), HttpStatus.OK);
//    }

    @PostMapping("/{pet_id}/image")
    public ResponseEntity<?> generateImage(@PathVariable Long pet_id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        String koJournal = chatGptService.generateJournal(pet_id, principalDetails).getAnswer();
        String shortKoJour = chatGptService.shortJorunal(koJournal).getAnswer(); //DALL-E에게 전달을 위한 1문장 요약
        System.out.print(shortKoJour);
        return new ResponseEntity<>(aiService.generatePicture(naverTransService.getTransSentence(shortKoJour)), HttpStatus.OK);
    }

}