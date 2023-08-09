package com.hack.hack_server.Authentication.Service;

import com.hack.hack_server.Authentication.Dto.JoinRequestDto;
import com.hack.hack_server.Authentication.Dto.LoginRequestDto;
import com.hack.hack_server.Authentication.JwtProvider;
import com.hack.hack_server.Community.Entity.User;
import com.hack.hack_server.Community.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.util.ClassUtils.isPresent;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;



    public String join(JoinRequestDto joinRequestDto) {
//        if (userRepository.findByEmail(joinRequestDto.getEmail()).isPresent()){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //이메일 중복 시 400 에러 반환
//        }
        String email=  joinRequestDto.getEmail();
        String username = joinRequestDto.getUsername();
        String password = joinRequestDto.getPassword();
        User user = new User(username, email, password);
        userRepository.save(user); // DB 저장
        return "회원가입";
    }

    public String login(LoginRequestDto loginRequestDto) {
        String email = loginRequestDto.getEmail();
        String rawPassword = loginRequestDto.getPassword();

        User byEmail = userRepository.findByEmail(email);

        // 비밀번호 일치 여부 확인
        if(passwordEncoder.matches(rawPassword, byEmail.getPassword())){
            String jwtToken = jwtProvider.generateJwtToken(byEmail.getId(), byEmail.getEmail(), byEmail.getNickname());

            // JWT 토큰 반환
            return "로그인 성공 " + jwtToken;
        }

        return "로그인 실패";
    }
}