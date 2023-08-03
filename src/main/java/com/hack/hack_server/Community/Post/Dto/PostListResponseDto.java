package com.hack.hack_server.Community.Post.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class PostListResponseDto {
    private List<PostResponseDto> postList;
    private int totalPage;
    private int currentPage;
}
