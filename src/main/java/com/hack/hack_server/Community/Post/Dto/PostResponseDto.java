package com.hack.hack_server.Community.Post.Dto;

import com.hack.hack_server.Entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class PostResponseDto {
    private Long id;
    private String nickname;
    private String title;
    private int likecount;
    private int viewcount;
    private Date created_date;

    public PostResponseDto(Post post){
        this.id = post.getId();
        this.nickname = post.getUser().getNickname();
        this.title = post.getTitle();
        this.likecount = post.getLikecount();
        this.viewcount = post.getViewcount();
        this.created_date = java.sql.Timestamp.valueOf(post.getCreated_date());
    }
}
