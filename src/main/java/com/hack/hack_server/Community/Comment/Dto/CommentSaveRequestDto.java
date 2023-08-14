package com.hack.hack_server.Community.Comment.Dto;

import com.hack.hack_server.Entity.Post;
import com.hack.hack_server.Entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentSaveRequestDto {
    private String comment;
}
