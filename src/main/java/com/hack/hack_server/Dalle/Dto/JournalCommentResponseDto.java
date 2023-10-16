package com.hack.hack_server.Dalle.Dto;

import com.hack.hack_server.Entity.Journal;
import com.hack.hack_server.Entity.JournalComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Getter
public class JournalCommentResponseDto {
    private String content;
    private LocalDateTime createdTime;

    public JournalCommentResponseDto(JournalComment journalComment){
        this.createdTime = journalComment.getCreatedTime();
        this.content= journalComment.getContent();
    }
}
