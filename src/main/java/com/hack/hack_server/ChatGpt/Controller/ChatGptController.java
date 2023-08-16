package com.hack.hack_server.ChatGpt.Controller;

import com.hack.hack_server.Authentication.PrincipalDetails;
import com.hack.hack_server.ChatGpt.Dto.ChatGptAnswerResponseDto;
import com.hack.hack_server.ChatGpt.Dto.QuestionRequestDto;
import com.hack.hack_server.ChatGpt.Service.ChatGptService;
import com.hack.hack_server.ChatGpt.Dto.ChatGptResponseDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rainbow-letter/chat")
public class ChatGptController {

    private final ChatGptService chatGptService;

    public ChatGptController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @PostMapping("/{pet_id}")
    public ChatGptAnswerResponseDto sendQuestion(@PathVariable Long pet_id, @AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody QuestionRequestDto requestDto) {
        return chatGptService.askQuestion(pet_id, principalDetails, requestDto);
    }
}