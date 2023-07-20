package com.hack.hack_server.Community.Dto;

import com.hack.hack_server.Community.Entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class PostResponseDto {
    private Long id;
    private String nickname;
    private String title;
    private Date created_date;

    public PostResponseDto(Post post){
        this.id = post.getId();
        this.nickname = post.getUser().getNickname();
        this.title = post.getTitle();
        this.created_date = java.sql.Timestamp.valueOf(post.getCreated_date());
    }
}
