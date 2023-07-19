package com.hack.hack_server.ChatGpt.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hack.hack_server.ChatGpt.Dto.MessageRequestDto;
import lombok.*;
import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChatGptRequestDto implements Serializable {

    private String model;
    //private String prompt;
    @JsonProperty("messages")
    private List<MessageRequestDto> messages;
    @JsonProperty("max_tokens")
    private Integer maxTokens;
    private Double temperature;
    @JsonProperty("top_p")
    private Double topP;

    @Builder
    public ChatGptRequestDto(String model, List<MessageRequestDto> messages,
                             Integer maxTokens, Double temperature,
                             Double topP) {
        this.model = model;
        this.messages = messages;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.topP = topP;
    }
}
