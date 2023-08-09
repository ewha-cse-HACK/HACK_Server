package com.hack.hack_server.Authentication;
import com.hack.hack_server.Authentication.Service.PrincipalDetailsService;
import com.hack.hack_server.Entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;


public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private JwtProvider jwtProvider;

    private PrincipalDetailsService principalDetailsService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtProvider jwtProvider, PrincipalDetailsService principalDetailsService ) {
        super(authenticationManager);
        this.jwtProvider = jwtProvider;
        this.principalDetailsService = principalDetailsService;
    }

    // 인증이나 권한이 필요한 주소 요청이 있을 때 해당 필터를 통과한다.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwtHeader = request.getHeader("Authorization");

        // header가 있는지 확인
        if (jwtHeader == null || !jwtHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);

            return;
        }

        // JWT 토큰을 검증하여 정상적인 사용자인지 확인
        String jwtToken = request.getHeader("Authorization").replace("Bearer ","");
        User tokenUser = jwtProvider.validToken(jwtToken);
        if( tokenUser != null){
            PrincipalDetails principalDetails = new PrincipalDetails(tokenUser);

            // JWT 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다.
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());


            // 시큐리티 세션에 Authentcation 을 저장한다.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }


        chain.doFilter(request, response);
    }
}