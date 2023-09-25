package com.hack.hack_server.ChatGpt.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DalleAnswerResponseDto {
    private String answer;
    private Long jourId;

    public DalleAnswerResponseDto(String answer, Long jourId){
        this.answer = answer;
        this.jourId = jourId;
    }
}
