package com.hack.hack_server.Community.Comment.Controller;

import com.hack.hack_server.Authentication.PrincipalDetails;
import com.hack.hack_server.Community.Comment.Dto.CommentSaveRequestDto;
import com.hack.hack_server.Community.Comment.Dto.CommentUpdateRequestDto;
import com.hack.hack_server.Community.Comment.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("community")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    @PostMapping("/{post_id}/save-comment")
    public ResponseEntity saveComment(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long post_id, @RequestBody CommentSaveRequestDto requestDto){
        commentService.saveComment(principalDetails, post_id, requestDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{post_id}/comment/{comment_id}")
    public ResponseEntity updateComment(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long comment_id, @RequestBody CommentUpdateRequestDto requestDto){
        return commentService.modifyComment(principalDetails, comment_id, requestDto);
    }

    @DeleteMapping("/{post_id}/comment/{comment_id}")
    public ResponseEntity deleteComment(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long comment_id){
        return commentService.deleteComment(principalDetails, comment_id);
    }
}
