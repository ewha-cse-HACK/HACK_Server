package com.hack.hack_server.Community.Post.Dto;

import lombok.Getter;

@Getter
public class PostModifyRequestDto {
    private String title;
    private String content;
    private Long userId;
}
