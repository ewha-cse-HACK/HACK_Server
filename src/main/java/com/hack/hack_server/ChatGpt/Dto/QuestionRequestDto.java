package com.hack.hack_server.ChatGpt.Dto;

import java.io.Serializable;
import lombok.*;
@Getter
public class QuestionRequestDto implements Serializable {
    private String question;
}