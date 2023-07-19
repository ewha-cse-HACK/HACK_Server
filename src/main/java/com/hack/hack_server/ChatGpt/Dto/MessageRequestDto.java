package com.hack.hack_server.ChatGpt.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class MessageRequestDto implements Serializable {
    private String role;  //chatGPT가 어느 관점에서 메시지를 작성할지 정해주기
    private String content;  //프론트에서 받은 질문 내용 전달
    private String name;   //질문한 사람 이름

    @Builder
    public MessageRequestDto(String role, String content, String name){
        this.role = role;
        this.content = content;
        this.name = name;
    }
}
