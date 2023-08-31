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
    private String nickname;
    private String content;
    private int likecount;
    private String profileImage;
    private List<CommentDto> commentList;
}
