package com.hack.hack_server.Community.Post.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PostDetailResponseDto {
    private Long userId;
    private Long writerId;
    private String nickname;
    private String content;
    private int likecount;
    private int viewcount;
    private boolean islike;
    private LocalDate createdDate;
    private String profileImage;
    private List<CommentDto> commentList;
    private int imageNumber;
    private List<PostImageDto> imageList;
}
