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
    private Date createdDate;

    public PostResponseDto(Post post){
        this.id = post.getId();
        this.nickname = post.getUser().getNickname();
        this.title = post.getTitle();
        this.likecount = post.getLikecount();
        this.viewcount = post.getViewcount();
        this.createdDate = java.sql.Timestamp.valueOf(post.getCreatedDate());
    }
}
