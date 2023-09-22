package com.hack.hack_server.MyPage.Controller;

import com.hack.hack_server.Authentication.PrincipalDetails;
import com.hack.hack_server.MyPage.Dto.MyPageRequestDto;
import com.hack.hack_server.MyPage.Service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rainbow-letter/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;
    @GetMapping()
    public MyPageRequestDto getMyInfo(@AuthenticationPrincipal PrincipalDetails principalDetails){
        return myPageService.getMyInfo(principalDetails);
    }

}
