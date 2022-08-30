package com.example.springshop.process.global.security.auth;

import com.example.springshop.process.domain.model.Role;
import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Failed: No User Info"));

        UserDetails userDetails = new PrincipalDetails(user, getUserDetails(user));
        return userDetails;
    }

    public static Collection<SimpleGrantedAuthority> getUserDetails(User user) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if(user.getRole().equals(Role.USER)){
            authorities.add(new SimpleGrantedAuthority("USER"));
        } else if(user.getRole().equals(Role.ADMIN)){
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }

        return authorities;
    }

}
//
//@Service
//@RequiredArgsConstructor
//public class PrincipalDetailsService implements UserDetailsService{
//
//    private final UserRepository userRepository;
//
//    LocalDateTime localTime = LocalDateTime.now();
//
//    @Transactional
//    @Builder
//    public void join(PrincipalDetails principalDetails){
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        User user = new User();
//        user.builder()
//                .password(passwordEncoder.encode(principalDetails.getPassword()))
//                .role(Role.USER)
//                .appendDate(localTime)
//                .updateDate(localTime)
//                .build();
//
//        principalDetails.builder()
//                .user(user)
//                .build();
//
//        userRepository.save(user);
//    }
//
//    @Override
//    public PrincipalDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        //여기서 받은 유저 패스워드와 비교하여 로그인 인증
//        PrincipalDetails principalDetails = userMapper.getUserAccount(email);
//        if (principalDetails == null){
//            throw new UsernameNotFoundException("User not authorized.");
//        }
//        return userVo;
//    }