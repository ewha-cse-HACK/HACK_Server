package com.hack.hack_server.Community.Post.Dto;

import lombok.Getter;

@Getter
public class PostAddRequestDto {
    private Long userid;
    private String title;
    private String content;

//    @Builder
//    public Post toEntity(User user){
//        return Post.builder()
//                .title(title)
//                .content(content)
//                .user(user)
//                .build();
//    }
}
