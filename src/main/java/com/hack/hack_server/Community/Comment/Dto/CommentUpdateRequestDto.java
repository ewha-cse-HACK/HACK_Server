package com.hack.hack_server.Community.Comment.Dto;

import lombok.Getter;

@Getter
public class CommentUpdateRequestDto {
    private String comment;
    private Long userId;  //나중에 로그인한 유저 정보 받아오기로 수정 후 삭제
}
