package com.hack.hack_server.Community.Comment.Controller;

import com.hack.hack_server.Community.Comment.Dto.CommentSaveRequestDto;
import com.hack.hack_server.Community.Comment.Dto.CommentUpdateRequestDto;
import com.hack.hack_server.Community.Comment.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rainbowletter/community")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    @PostMapping("/{post_id}/save-comment")
    public ResponseEntity saveComment(@PathVariable Long post_id, @RequestBody CommentSaveRequestDto requestDto){
        commentService.saveComment(post_id, requestDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{post_id}/comment/{comment_id}")
    public ResponseEntity updateComment(@PathVariable Long comment_id, @RequestBody CommentUpdateRequestDto requestDto){
        return commentService.modifyComment(comment_id, requestDto);
    }

    @DeleteMapping("/{post_id}/comment/{comment_id}")
    public ResponseEntity deleteComment(@PathVariable Long comment_id){
        return commentService.deleteComment(comment_id);
    }
}
