package com.example.springshop.process.global.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springshop.process.domain.user.dto.request.UserLoginRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    // login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("JwtAuthenticationFilter : 로그인 요청");

        // request에 있는 username과 password를 파싱해 자바 Object로 받기
        ObjectMapper om = new ObjectMapper();
        UserLoginRequestDto userLoginRequestDto = null;
        try {
            userLoginRequestDto = om.readValue(request.getInputStream(), UserLoginRequestDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("JwtAuthenticationFilter : 요청자 ID: " + userLoginRequestDto.getEmail());

        // UsernamePasswordToken 생성.
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        userLoginRequestDto.getEmail(),
                        userLoginRequestDto.getPassword());
        System.out.println("JwtAuthenticationFilter : Jwt토큰 생성 완료");

        // PrincipalDetail의 loadUserByUsername() 함수 실행됨
        Authentication authentication =
                authenticationManager.authenticate(authenticationToken);
        // authentication 객체가 session 영역에 저장 -> 로그인 성공
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println("Authentication : " + userDetails);
        return authentication;
    }

    // JWT Token 생성 후 response에 담기

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();

        String jwtToken = JWT.create()
                        .withIssuer("limhaekyu") // 발행자
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME)) // 토큰 유효기간
                        .withClaim("email", userDetails.getUser().getEmail()) // 토큰에 담은 정보
                        .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        System.out.println(userDetails.getUser().getEmail());

        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
    }
}
