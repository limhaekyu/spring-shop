package com.example.springshop.process.global.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("--------Start check Authorization in JWT token--------");
        // 클라이언트가 요청한 헤더에 Authorization이 있는지 검증 / 없으면 다시 필터 타게
        String header = request.getHeader(JwtProperties.HEADER_STRING);
        if(header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)){
            chain.doFilter(request, response);
            return ;
        }
        System.out.println("header:  " + header);

        // JWT 토큰을 검증 후 정상적인 사용자인지 확인
        // 헤더에 키가 Authorization이면 값에 Bearer 뒤에 " "를 없앤다(토큰 값만 추출)
        System.out.println("Token 검증 시작");

        String token = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX, "");

        // 토큰 서명 후 검증해서 통과하면 email 가져온다.
        String email = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
                .getClaim("email").asString();

        // 서명이 정상적으로 됐다면
        if(email != null){
            User user = userRepository.findByEmail(email)
                    .orElseThrow( ()-> new UsernameNotFoundException("없는 이메일 입니다."));

            // security가 수행해주는 권한 처리를 위해 토크능ㄹ 만들어서 Authentication 객체를 강제로 만들고 그걸 세션에 저장
            UserDetailsImpl userDetails = new UserDetailsImpl(user, UserDetailsServiceImpl.getUserDetails(user));
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            // 강제로 시큐리티의 세션에 접근해 값 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        System.out.println("--------Finish check Authorization in JWT token--------");
        chain.doFilter(request, response);
    }
}
