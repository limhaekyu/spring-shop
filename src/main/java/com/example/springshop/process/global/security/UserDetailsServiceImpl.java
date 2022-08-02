package com.example.springshop.process.global.security;

import com.example.springshop.process.domain.model.Role;
import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("없는 이메일입니다."));
        UserDetails userDetails = new UserDetailsImpl(user, getUserDetails(user));
        return userDetails;
    }

    static Collection<SimpleGrantedAuthority> getUserDetails(User user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if(user.getRole().equals(Role.USER)){
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        } else if(user.getRole().equals(Role.ADMIN)){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return authorities;
    }

}
