package com.hack.hack_server.Community.Service;

import com.hack.hack_server.Community.Dto.PostListResponseDto;
import com.hack.hack_server.Community.Dto.PostResponseDto;
import com.hack.hack_server.Community.Entity.Post;
import com.hack.hack_server.Community.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public PostListResponseDto findAllPost(Pageable pageable){
        Page<Post> posts = postRepository.findAll(pageable);
        Page<PostResponseDto> postResponseDtos = posts.map(PostResponseDto::new);
        PostListResponseDto responseDto = PostListResponseDto.builder()
                .postList(postResponseDtos.getContent())
                .currentPage(postResponseDtos.getNumber() + 1)
                .totalPage(postResponseDtos.getTotalPages())
                .build();
        return responseDto;
    }
}
