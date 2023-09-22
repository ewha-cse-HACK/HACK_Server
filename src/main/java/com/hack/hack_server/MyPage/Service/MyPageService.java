package com.hack.hack_server.MyPage.Service;

import com.hack.hack_server.Authentication.PrincipalDetails;
import com.hack.hack_server.Entity.User;
import com.hack.hack_server.MyPage.Dto.MyPageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {

    public MyPageRequestDto getMyInfo(PrincipalDetails principalDetails){
        User user = principalDetails.getUser();

        MyPageRequestDto requestDto = MyPageRequestDto.builder()
                .email(user.getEmail())
                .profileImage(user.getProfileImage())
                .nickname(user.getNickname())
                .build();
        return  requestDto;
    }
}
