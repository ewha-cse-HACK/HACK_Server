package com.hack.hack_server.Community.Post.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PostDetailResponseDto {
    private Long userId;
    private Long writerId;
    private String writer;
    private String content;
    private int likecount;
//    private 개인프로필, 글 사진
    private List<CommentDto> commentList;
}
