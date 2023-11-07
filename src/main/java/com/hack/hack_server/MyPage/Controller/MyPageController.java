package com.hack.hack_server.MyPage.Controller;

import com.hack.hack_server.Authentication.PrincipalDetails;
import com.hack.hack_server.MyPage.Dto.MyPageRequestDto;
import com.hack.hack_server.MyPage.Dto.NickNameModifyRequestDto;
import com.hack.hack_server.MyPage.Dto.ProfileImageModifyRequestDto;
import com.hack.hack_server.MyPage.Service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping()
    public MyPageRequestDto getMyInfo(@AuthenticationPrincipal PrincipalDetails principalDetails){
        return myPageService.getMyInfo(principalDetails);
    }

    @PutMapping("/profile-image/delete")
    public ResponseEntity deleteProfileImage(@AuthenticationPrincipal PrincipalDetails principalDetails){
        return myPageService.deleteProfileImage(principalDetails);
    }

    @PutMapping("/profile-image/modify")
    public ResponseEntity modifyProfileImage(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody ProfileImageModifyRequestDto requestDto){
        return myPageService.modifyProfileImage(principalDetails, requestDto);
    }

    @PutMapping("/nickname/modify")
    public ResponseEntity modifyNickname(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody NickNameModifyRequestDto requestDto){
        return myPageService.modifyNickName(principalDetails, requestDto);
    }

}
