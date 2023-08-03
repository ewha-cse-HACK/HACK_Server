package com.hack.hack_server.Community.Dto;

import com.hack.hack_server.Community.Entity.Post;
import com.hack.hack_server.Community.Entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
