package com.hack.hack_server.Dalle.Dto;

import com.hack.hack_server.Entity.Journal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;

@Getter
@Builder
public class JournalCommentDto {
    private String content;
    private User user;
    private Journal journal;

    public JournalCommentDto(String content, User user, Journal journal){
        this.content = content;
        this.user = user;
        this.journal = journal;
    }

}
