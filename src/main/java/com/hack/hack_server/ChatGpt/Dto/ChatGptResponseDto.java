package com.hack.hack_server.ChatGpt.Dto;

import com.hack.hack_server.ChatGpt.Dto.Choice;
import lombok.*;

import java.io.Serializable;
import java.time.*;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChatGptResponseDto implements Serializable {

    private String id;
    private String object;
    private LocalDate created;
    private String model;
    private List<Choice> choices;

    @Builder
    public ChatGptResponseDto(String id, String object,
                              LocalDate created, String model,
                              List<Choice> choices) {
        this.id = id;
        this.object = object;
        this.created = created;
        this.model = model;
        this.choices = choices;
    }
}
