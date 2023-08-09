package com.hack.hack_server.Community.Comment.Service;

import com.hack.hack_server.Community.Comment.Dto.CommentSaveRequestDto;
import com.hack.hack_server.Community.Comment.Dto.CommentUpdateRequestDto;
import com.hack.hack_server.Entity.Comment;
import com.hack.hack_server.Entity.Post;
import com.hack.hack_server.Entity.User;
import com.hack.hack_server.Repository.CommentRepository;
import com.hack.hack_server.Repository.PostRepository;
import com.hack.hack_server.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    @Transactional
    public void saveComment(Long postId, CommentSaveRequestDto requestDto){
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다: " + requestDto.getUserId()));
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다: " + postId));
        Comment comment = Comment.builder()
                .comment(requestDto.getComment())
                .user(user)
                .post(post)
                .build();
        commentRepository.save(comment);
    }

    @Transactional
    public ResponseEntity modifyComment(Long commentId, CommentUpdateRequestDto requestDto){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글 없습니다: " + commentId));
        if (requestDto.getUserId() != comment.getUser().getId()) //현재 수정하려는 사용자와 댓글 작성자가 다르면
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        comment.update(requestDto.getComment());
        return new ResponseEntity(HttpStatus.OK);
    }
}
