package com.hack.hack_server.Dalle.Dto;

import com.hack.hack_server.Entity.Journal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class JournalResponseDto {
    private Long id;
    private LocalDateTime createdTime;
    private String content;
    private String imageUrl;
    private JournalCommentResponseDto journalCommentResponseDto;

    //그림일기 1개 조회
    public JournalResponseDto(Journal journal, String imageUrl, JournalCommentResponseDto journalCommentResponseDto){
        this.createdTime = journal.getCreatedTime();
        this.content= journal.getContent();
        this.imageUrl = imageUrl;
        this.journalCommentResponseDto = journalCommentResponseDto;
    }

    //그림일기 목록 조회
    public JournalResponseDto(Journal journal){
        this.id = journal.getId();
        this.createdTime = journal.getCreatedTime();
    }

}
