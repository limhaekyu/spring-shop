package com.example.springshop.process.global.security;

public interface JwtProperties {
    String SECRET = "shop";
    int EXPIRATION_TIME = 60*60*30;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
