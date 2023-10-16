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
public class JournalListDto {
    private Long id;
    private LocalDateTime createdTime;

    //그림일기 목록 조회
    public JournalListDto(Journal journal){
        this.id = journal.getId();
        this.createdTime = journal.getCreatedTime();
    }

}
