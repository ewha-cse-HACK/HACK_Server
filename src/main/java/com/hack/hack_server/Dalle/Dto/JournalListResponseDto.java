package com.hack.hack_server.Dalle.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class JournalListResponseDto {
    private List<JournalListDto> journalList;
}
