package com.hack.hack_server.Community.Post.Service;

import com.hack.hack_server.Entity.Post;
import com.hack.hack_server.Entity.User;
import com.hack.hack_server.Community.Post.Dto.*;
import com.hack.hack_server.Repository.PostRepository;
import com.hack.hack_server.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public PostListResponseDto findAllPost(Pageable pageable){
        Page<Post> posts = postRepository.findAllByDelIsFalse(pageable);
        Page<PostResponseDto> postResponseDtos = posts.map(PostResponseDto::new);
        PostListResponseDto responseDto = PostListResponseDto.builder()
                .postList(postResponseDtos.getContent())
                .currentPage(postResponseDtos.getNumber() + 1)
                .totalPage(postResponseDtos.getTotalPages())
                .build();
        return responseDto;
    }

    @Transactional
    public PostDetailResponseDto findDetailPost(Long post_id){
        Post post = postRepository.findById(post_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다: " + post_id));;
        post.setViewcount(post.getViewcount() + 1);

        PostDetailResponseDto responseDto = PostDetailResponseDto.builder()
                .writer(post.getUser().getNickname())
                .content(post.getContent())
                .likecount(post.getLikecount())
                .build();
        return responseDto;
    }

    @Transactional
    public void modifyPost(Long post_id, PostModifyRequestDto requestDto){
        Post post = postRepository.findById(post_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다: " + post_id));
        post.update(requestDto.getTitle(), requestDto.getContent());
    }

    @Transactional
    public void deletePost(Long post_id){
        Post post = postRepository.findById(post_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다: " + post_id));
        if (post.isDel() == false)
            post.setDel(true);
    }

    @Transactional
    public void savePost(PostAddRequestDto requestDto){
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다: " + requestDto.getUserId()));
        Post post = Post.builder()
                .user(user)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .build();

        postRepository.save(post);
    }
}
