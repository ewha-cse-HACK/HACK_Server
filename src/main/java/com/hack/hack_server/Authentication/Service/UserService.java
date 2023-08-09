package com.hack.hack_server.Authentication.Service;

import com.hack.hack_server.Authentication.Dto.JoinRequestDto;
import com.hack.hack_server.Authentication.Dto.LoginRequestDto;
import com.hack.hack_server.Authentication.JwtProvider;
import com.hack.hack_server.Entity.User;
import com.hack.hack_server.Repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public String join(JoinRequestDto joinRequestDto) {
        String email=  joinRequestDto.getEmail();
        String nickname = joinRequestDto.getNickname();
        String rawPassword = joinRequestDto.getPassword();
        String password = passwordEncoder.encode(rawPassword);
        User user = new User(nickname, email, password);
        userRepository.save(user); // DB 저장
        return "회원가입";
    }

    public String login(LoginRequestDto loginRequestDto) {
        String email = loginRequestDto.getEmail();
        String rawPassword = loginRequestDto.getPassword();

        User user = userRepository.findByEmail(email);

        // 비밀번호 일치 여부 확인
        if(passwordEncoder.matches(rawPassword, user.getPassword())){
            String jwtToken = jwtProvider.generateJwtToken(user.getId(), user.getEmail(), user.getNickname());

            // JWT 토큰 반환
            return "로그인 성공 " + jwtToken;
        }

        return "로그인 실패";
    }
}