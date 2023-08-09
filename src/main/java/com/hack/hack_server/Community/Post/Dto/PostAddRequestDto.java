package com.hack.hack_server.Community.Post.Dto;

import lombok.Getter;

@Getter
public class PostAddRequestDto {
    private Long userId;
    private String title;
    private String content;

}
