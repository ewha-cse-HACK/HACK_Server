package com.hack.hack_server.Community.Comment.Dto;

import com.hack.hack_server.Entity.Post;
import com.hack.hack_server.Entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentSaveRequestDto {
    private String comment;
    private Long userId;  //추후에 로그인한 유저의 정보 받아오기로 변경
}
