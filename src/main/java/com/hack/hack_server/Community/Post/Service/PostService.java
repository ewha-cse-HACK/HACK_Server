package com.hack.hack_server.Community.Post.Service;

import com.hack.hack_server.Authentication.PrincipalDetails;
import com.hack.hack_server.Entity.Comment;
import com.hack.hack_server.Entity.Post;
import com.hack.hack_server.Entity.PostImage;
import com.hack.hack_server.Entity.User;
import com.hack.hack_server.Community.Post.Dto.*;
import com.hack.hack_server.Repository.CommentRepository;
import com.hack.hack_server.Repository.PostImageRepository;
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
    private final PostImageRepository postImageRepository;
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
    public PostDetailResponseDto findDetailPost(PrincipalDetails principalDetails, Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다: " + postId));
        post.setViewcount(post.getViewcount() + 1);

        List<Comment> commentList = commentRepository.findByPost_Id(postId);
        List<CommentDto> commentDtos = commentList.stream().map(CommentDto::new).collect(Collectors.toList());

        List<PostImage> imageList = postImageRepository.findPostImageByPost_Id(postId);
        List<PostImageDto> postImageDtos = imageList.stream().map(PostImageDto::new).collect(Collectors.toList());

        PostDetailResponseDto responseDto = PostDetailResponseDto.builder()
                .userId(principalDetails.getUser().getId())
                .writerId(post.getUser().getId())
                .nickname(post.getUser().getNickname())
                .content(post.getContent())
                .likecount(post.getLikecount())
                .profileImage(post.getUser().getProfileImage())
                .commentList(commentDtos)
                .imageList(postImageDtos)
                .build();
        return responseDto;
    }

    @Transactional
    public ResponseEntity modifyPost(PrincipalDetails principalDetails, Long post_id, PostModifyRequestDto requestDto){
        User user = principalDetails.getUser();
        Post post = postRepository.findById(post_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다: " + post_id));
        if (post.getUser().getId() != user.getId())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        post.update(requestDto.getTitle(), requestDto.getContent());
        return new ResponseEntity(HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity deletePost(PrincipalDetails principalDetails, Long post_id){
        User user = principalDetails.getUser();
        Post post = postRepository.findById(post_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다: " + post_id));
        if (post.getUser().getId() != user.getId())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        if (post.isDel() == false)
            post.setDel(true);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity savePost(PrincipalDetails principalDetails, PostAddRequestDto requestDto){
        User user = principalDetails.getUser();
        Post post = Post.builder()
                .user(user)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .build();
        postRepository.save(post);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
