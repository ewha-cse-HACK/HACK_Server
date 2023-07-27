package com.hack.hack_server.Community.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PostDetailResponseDto {
    private String writer;
    private String content;
    private int likecount;
//    private 개인프로필, 글 사진
//    private List<Comment~>
}
