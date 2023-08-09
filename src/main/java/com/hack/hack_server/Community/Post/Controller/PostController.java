package com.hack.hack_server.Community.Post.Controller;

import com.hack.hack_server.Community.Post.Dto.PostAddRequestDto;
import com.hack.hack_server.Community.Post.Dto.PostDetailResponseDto;
import com.hack.hack_server.Community.Post.Dto.PostListResponseDto;
import com.hack.hack_server.Community.Post.Dto.PostModifyRequestDto;
import com.hack.hack_server.Community.Post.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "rainbowletter/community")
public class PostController {

    private final PostService postService;
    @GetMapping
    public PostListResponseDto getAllPost(@RequestParam(value = "page", defaultValue = "1", required = false) int page){
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("id").descending());
        return postService.findAllPost(pageable);
    }

    @GetMapping("/{post_id}")
    public PostDetailResponseDto getDetailPost(@PathVariable Long post_id){
        return postService.findDetailPost(post_id);
    }

    @PutMapping("/{post_id}")
    public ResponseEntity modifyPost(@PathVariable Long post_id, @RequestBody PostModifyRequestDto requestDto){
        if (requestDto.getContent().isEmpty())
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        postService.modifyPost(post_id, requestDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping ("/{post_id}/delete")
    public ResponseEntity deletePost(@PathVariable Long post_id){
        postService.deletePost(post_id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/write")
    public void writingPost(){
        return ;
    }

    @PostMapping("/save-post")
    public void savePost(@RequestBody PostAddRequestDto requestDto){
        postService.savePost(requestDto);
    }
}
