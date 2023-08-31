package com.hack.hack_server.Community.Post.Dto;

import com.hack.hack_server.Entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentDto {
    private Long writerId;
    private String nickname;
    private String profileImage;
    private String comment;

    public CommentDto(Comment comment){
        this.writerId = comment.getUser().getId();
        this.nickname = comment.getUser().getNickname();
        this.comment = comment.getComment();
        this.profileImage = comment.getUser().getProfileImage();
    }

}
