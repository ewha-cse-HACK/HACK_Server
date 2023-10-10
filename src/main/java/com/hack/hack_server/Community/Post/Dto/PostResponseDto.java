package com.hack.hack_server.Community.Post.Dto;

import com.hack.hack_server.Entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class PostResponseDto {
    private Long id;
    private String nickname;
    private String title;
    private int likecount;
    private int viewcount;
    private LocalDate createdDate;
    private String thumbnail;

    public PostResponseDto(Post post){
        this.id = post.getId();
        this.nickname = post.getUser().getNickname();
        this.title = post.getTitle();
        this.likecount = post.getLikecount();
        this.viewcount = post.getViewcount();
        this.createdDate = post.getCreatedTime().toLocalDate();
        this.thumbnail = post.getThumbnail();
    }
}
