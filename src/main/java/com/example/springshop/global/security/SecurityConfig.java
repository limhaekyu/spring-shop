package com.example.springshop.global.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // login page custom
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                // csrf 토큰 검사를 비활성화하는 로직
                .csrf()
                    .disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                    .and()
                    // form을 통한 login 활성화, custom login page 지정
                    .formLogin()
                        .loginPage("/login")
                        .permitAll();
    }



}
