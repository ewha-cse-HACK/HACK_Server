package com.hack.hack_server.Authentication.Controller;

import com.hack.hack_server.Authentication.Dto.JoinRequestDto;
import com.hack.hack_server.Authentication.Dto.LoginRequestDto;
import com.hack.hack_server.Authentication.PrincipalDetails;
import com.hack.hack_server.Authentication.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/api/info")
    public String info(@AuthenticationPrincipal PrincipalDetails principalDetails, Authentication authentication) {
        System.out.println("PrincipalDetails " + principalDetails);
        System.out.println("authentication " + authentication);

        StringBuilder sb = new StringBuilder();
        sb.append("PrincipalDetails ");
        sb.append(principalDetails);
        sb.append("\n\n");
        sb.append("authentication ");
        sb.append(authentication);

        return sb.toString();

    }
}
