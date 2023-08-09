package com.hack.hack_server.Authentication.Controller;

import com.hack.hack_server.Authentication.Dto.JoinRequestDto;
import com.hack.hack_server.Authentication.Dto.LoginRequestDto;
import com.hack.hack_server.Authentication.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/join")
    public String join(@RequestBody JoinRequestDto joinRequestDto) {
        return userService.join(joinRequestDto);
    }

    @PostMapping("/api/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto);
    }
}
