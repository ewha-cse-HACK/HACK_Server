package com.hack.hack_server.ChatGpt.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatGptAnswerResponseDto {
    private String answer;

    public ChatGptAnswerResponseDto(String answer){
        this.answer = answer;
    }
}
