package com.hack.hack_server.MyPage.Dto;

import com.hack.hack_server.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MyPageRequestDto {
    private String nickname;
    private String email;
    private String profileImage;
}
