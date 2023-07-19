package com.hack.hack_server.ChatGpt.Service;

import com.hack.hack_server.ChatGpt.Dto.MessageRequestDto;
import com.hack.hack_server.ChatGpt.Dto.QuestionRequestDto;
import com.hack.hack_server.ChatGptConfig;
import com.hack.hack_server.ChatGpt.Dto.ChatGptRequestDto;
import com.hack.hack_server.ChatGpt.Dto.ChatGptResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatGptService {
    private static final RestTemplate restTemplate = new RestTemplate();

    @Value("${api-key.chat-gpt}")
    private String apiKey;

    public HttpEntity<ChatGptRequestDto> buildHttpEntity(ChatGptRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + apiKey);
        return new HttpEntity<>(requestDto, headers);
    }

    public ChatGptResponseDto getResponse(HttpEntity<ChatGptRequestDto> chatGptRequestDtoHttpEntity) {
        ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.URL,
                chatGptRequestDtoHttpEntity,
                ChatGptResponseDto.class);
        return responseEntity.getBody();
    }

    public ChatGptResponseDto askQuestion(QuestionRequestDto requestDto) {
        List<MessageRequestDto> messages = new ArrayList<>();
        messages.add(MessageRequestDto.builder()
                        .role(ChatGptConfig.SYSTEM_ROLE)
                        .content("You are a dead pet. Your master is missing you. Give comfort and warm words to your master. Use informal language")
                        .name("sister")
                        .build());
        messages.add(MessageRequestDto.builder()
                        .role(ChatGptConfig.USER_ROLE)
                        .content(requestDto.getQuestion())
                        .name("sister")
                        .build());

        return this.getResponse(
                this.buildHttpEntity(
                        new ChatGptRequestDto(
                                ChatGptConfig.MODEL,
                                messages,
                                ChatGptConfig.MAX_TOKEN,
                                ChatGptConfig.TEMPERATURE,
                                ChatGptConfig.TOP_P
                        )
                )
        );
    }
}