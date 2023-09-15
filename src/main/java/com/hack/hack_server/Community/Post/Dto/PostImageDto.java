package com.hack.hack_server.Community.Post.Dto;

import com.hack.hack_server.Entity.PostImage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostImageDto {
    private String imageUrl;

    public PostImageDto(PostImage postImage){
        this.imageUrl = postImage.getImageUrl();
    }
}
