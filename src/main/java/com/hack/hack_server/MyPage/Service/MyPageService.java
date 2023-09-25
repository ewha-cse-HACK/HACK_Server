package com.hack.hack_server.MyPage.Service;

import com.hack.hack_server.Authentication.PrincipalDetails;
import com.hack.hack_server.Entity.User;
import com.hack.hack_server.MyPage.Dto.MyPageRequestDto;
import com.hack.hack_server.MyPage.Dto.ProfileImageModifyRequestDto;
import com.hack.hack_server.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;

    @Transactional
    public MyPageRequestDto getMyInfo(PrincipalDetails principalDetails){
        User user = principalDetails.getUser();

        MyPageRequestDto requestDto = MyPageRequestDto.builder()
                .email(user.getEmail())
                .profileImage(user.getProfileImage())
                .nickname(user.getNickname())
                .build();
        return  requestDto;
    }

    @Transactional
    public ResponseEntity deleteProfileImage(PrincipalDetails principalDetails){
        User user = principalDetails.getUser();

        user.updateProfile(null);
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Transactional
    public ResponseEntity modifyProfileImage(PrincipalDetails principalDetails, ProfileImageModifyRequestDto requestDto){
        User user = principalDetails.getUser();

        user.updateProfile(requestDto.getProfileImage());
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
