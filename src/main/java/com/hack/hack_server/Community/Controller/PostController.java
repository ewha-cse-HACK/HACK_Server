package com.hack.hack_server.Community.Controller;

import com.hack.hack_server.Community.Dto.PostListResponseDto;
import com.hack.hack_server.Community.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("rainbowletter/community")
public class PostController {

    private final PostService postService;
    @GetMapping
    public PostListResponseDto getAllpost(int page){
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("id").descending());
        return postService.findAllPost(pageable);
    }

}
