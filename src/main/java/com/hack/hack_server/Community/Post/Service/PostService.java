package com.hack.hack_server.Community.Post.Service;

import com.hack.hack_server.Entity.Comment;
import com.hack.hack_server.Entity.Post;
import com.hack.hack_server.Entity.User;
import com.hack.hack_server.Community.Post.Dto.*;
import com.hack.hack_server.Repository.CommentRepository;
import com.hack.hack_server.Repository.PostRepository;
import com.hack.hack_server.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

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
    public PostDetailResponseDto findDetailPost(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다: " + postId));
        post.setViewcount(post.getViewcount() + 1);

        List<Comment> commentList = commentRepository.findByPost_Id(postId);
        List<CommentDto> commentDtos = commentList.stream().map(CommentDto::new).collect(Collectors.toList());

        PostDetailResponseDto responseDto = PostDetailResponseDto.builder()
                .writer(post.getUser().getNickname())
                .content(post.getContent())
                .likecount(post.getLikecount())
                .commentList(commentDtos)
                .build();
        return responseDto;
    }

    @Transactional
    public ResponseEntity modifyPost(Long post_id, PostModifyRequestDto requestDto){
        Post post = postRepository.findById(post_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다: " + post_id));
        if (post.getUser().getId() != requestDto.getUserId())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        post.update(requestDto.getTitle(), requestDto.getContent());
        return new ResponseEntity(HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity deletePost(Long post_id){
        Post post = postRepository.findById(post_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다: " + post_id));
        //        if (post.getUser().getId() != userId) 면 400 반환 후 수정 못하도록
        if (post.isDel() == false)
            post.setDel(true);
        return new ResponseEntity(HttpStatus.OK);
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
