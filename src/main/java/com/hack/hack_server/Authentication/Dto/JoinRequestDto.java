package com.hack.hack_server.Authentication.Dto;

import com.hack.hack_server.Community.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinRequestDto {
    private String email;
    private String username;
    private String password;
}