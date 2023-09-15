package com.hack.hack_server.Community.Post.Dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PostAddRequestDto {
    private String title;
    private String content;
    private List<PostImageDto> imageList;
}
