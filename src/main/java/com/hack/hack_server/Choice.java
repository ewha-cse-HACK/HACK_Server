package com.hack.hack_server;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
public class Choice implements Serializable {

    //private String text;
    private Integer index;
    private MessageResponseDto message;
    @JsonProperty("finish_reason")
    private String finishReason;


    @Builder
    public Choice(Integer index, MessageResponseDto message, String finishReason) {
        //this.text = text;
        this.index = index;
        this.message = message;
        this.finishReason = finishReason;
    }
}
