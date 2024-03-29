package com.example.springshop.process.global.security;

import com.example.springshop.process.domain.user.repository.UserRepository;
import com.example.springshop.process.domain.user.service.UserService;
import com.example.springshop.process.global.security.auth.PrincipalDetails;
import com.example.springshop.process.global.security.auth.PrincipalDetailsService;
import com.example.springshop.process.global.security.jwt.JwtAuthenticationFilter;
import com.example.springshop.process.global.security.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;
    private final PrincipalDetailsService principalDetailsService;

    // 암호화에 필요한 PasswordEncoder를 Bean 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable(); // csrf 보안 토큰 설정 헤제
        http
                .headers().frameOptions().disable();
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT토큰 기반 인증 -> 세션 사용하지 않는다.
                .and()
                .httpBasic().disable() // rest api를 고려해 기본 설정을 해제
                .formLogin().disable()

                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))

                .authorizeRequests()
                .antMatchers("/loginPage", "/join", "/joinPage", "/login_success", "/login_failed", "/resources/**").permitAll() // 로그인 권한 누구나, resources 파일도 모든 권한
                // USER, ADMIN 접근 허용
                .antMatchers("/userSuccess").hasRole("USER")
                .antMatchers("/userSuccess").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/loginPage")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/login_success")
                .failureUrl("/login_failed")
                .and()
                .csrf().disable();
//                .antMatchers("/").permitAll()
//                .anyRequest().permitAll();
//                .antMatchers("/api/shop/join").permitAll()
//                .antMatchers("/login").permitAll()
//                .anyRequest().authenticated();
    }

    /**
     * 로그인 인증 처리 메소드
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailsService).passwordEncoder(passwordEncoder());
    }



}