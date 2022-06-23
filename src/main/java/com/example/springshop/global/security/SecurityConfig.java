package com.example.springshop.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // login page custom
    @Override
    protected void configure(HttpSecurity http) throws Exception{

        String[] staticResources  =  {
                "/css/**",
                "/images/**",
                "/fonts/**",
                "/scripts/**",
        };

        http
            // csrf 토큰 검사를 비활성화하는 로직
            .csrf()
                .disable()
            .authorizeRequests()
                .antMatchers(staticResources).permitAll()
                .anyRequest().authenticated()
                .and()
                // form을 통한 login 활성화, custom login page 지정
            .formLogin()
                .loginPage("/login")
//                .defaultSuccessUrl("/")
//                .usernameParameter("")
//                .failureUrl("")
                .and()
            .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher(""))
//                .logoutSuccessUrl("/")
                .permitAll();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}