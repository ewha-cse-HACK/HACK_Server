package com.hack.hack_server.Community.Post.Service;

import com.hack.hack_server.Authentication.PrincipalDetails;
import com.hack.hack_server.Entity.*;
import com.hack.hack_server.Community.Post.Dto.*;
import com.hack.hack_server.Repository.*;
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
    private final HeartRepository heartRepository;

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
                .viewcount(post.getViewcount())
                .islike(heartRepository.existsByUserAndPost(principalDetails.getUser(), post))
                .profileImage(post.getUser().getProfileImage())
                .commentList(commentDtos)
                .imageList(postImageDtos)
                .imageNumber(postImageDtos.size())
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

        postImageRepository.deletePostImageByPost_Id(post_id);

        if (requestDto.getImageList() != null){
            for(int i=0;i<requestDto.getImageList().size();i++) {
                PostImageDto p = requestDto.getImageList().get(i);

                PostImage img = PostImage.builder()
                        .post(post)
                        .imageUrl(p.getImageUrl())
                        .build();
                postImageRepository.save(img);
            }
        }
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

        if (requestDto.getImageList() != null){
            for(int i=0;i<requestDto.getImageList().size();i++){
                PostImageDto p = requestDto.getImageList().get(i);

                PostImage img = PostImage.builder()
                        .post(post)
                        .imageUrl(p.getImageUrl())
                        .build();
                postImageRepository.save(img);
            }
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity toggleHeart(PrincipalDetails principalDetails, Long post_id){
        Post post = postRepository.findById(post_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다: " + post_id));
        User user = principalDetails.getUser();
        if (heartRepository.existsByUserAndPost(user, post) == false){  //좋아요 테이블에 없으면
            post.setLikecount(post.getLikecount() + 1);
            Heart heart = Heart.builder()
                    .post(post)
                    .user(user)
                    .build();
            heartRepository.save(heart);
        }else{
            post.setLikecount(post.getLikecount() - 1);
            heartRepository.deleteByUserAndPost(user, post);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
