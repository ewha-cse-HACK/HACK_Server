package com.hack.hack_server.Community.Dto;

import com.hack.hack_server.Community.Entity.Post;
import com.hack.hack_server.Community.Entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostAddRequestDto {
    private Long userId;
    private String title;
    private String content;

    @Builder
    public Post toEntity(User user){
        return Post.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
    }
}
