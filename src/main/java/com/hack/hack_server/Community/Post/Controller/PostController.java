package com.hack.hack_server.Community.Post.Controller;

import com.hack.hack_server.Authentication.PrincipalDetails;
import com.hack.hack_server.Community.Post.Dto.PostAddRequestDto;
import com.hack.hack_server.Community.Post.Dto.PostDetailResponseDto;
import com.hack.hack_server.Community.Post.Dto.PostListResponseDto;
import com.hack.hack_server.Community.Post.Dto.PostModifyRequestDto;
import com.hack.hack_server.Community.Post.Service.PostService;
import com.hack.hack_server.Global.S3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "rainbow-letter/community")
public class PostController {

    private final PostService postService;
    @GetMapping
    public PostListResponseDto getAllPost(@RequestParam(value = "page", defaultValue = "1", required = false) int page){
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("id").descending());
        return postService.findAllPost(pageable);
    }

    @GetMapping("/{post_id}")
    public PostDetailResponseDto getDetailPost(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long post_id){
        return postService.findDetailPost(principalDetails, post_id);
    }

    @PutMapping("/{post_id}")
    public ResponseEntity modifyPost(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long post_id, @RequestBody PostModifyRequestDto requestDto){
        if (requestDto.getContent().isEmpty())
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return postService.modifyPost(principalDetails, post_id, requestDto);
    }

    @DeleteMapping ("/{post_id}/delete")
    public ResponseEntity deletePost(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long post_id){
        return postService.deletePost(principalDetails, post_id);
    }

    @GetMapping("/write")
    public void writingPost(){
        return ;
    }

    @PostMapping("/save-post")
    public ResponseEntity savePost(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody PostAddRequestDto requestDto){
        return postService.savePost(principalDetails, requestDto);
    }

    @PutMapping("/{post_id}/heart")
    public ResponseEntity toggleHeart(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long post_id){
        return postService.toggleHeart(principalDetails, post_id);
    }
}
