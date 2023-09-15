package com.hack.hack_server.Community.Post.Dto;

import com.hack.hack_server.Entity.PostImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostImageDto {
    private String imageUrl;

    public PostImageDto(PostImage postImage){
        this.imageUrl = postImage.getImageUrl();
    }
}
