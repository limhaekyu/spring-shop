package com.example.springshop.process.global.security.auth;

import com.example.springshop.process.domain.user.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class PrincipalDetails implements UserDetails {

    private final User user;

    private Collection<SimpleGrantedAuthority> authorities;

    public PrincipalDetails(User user, Collection<SimpleGrantedAuthority> authorities){
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword()
    {
        return user.getPassword();
    }
    // security의 username -> Email로 인증
    @Override
    public String getUsername(){
        return user.getEmail();
    }

    // Principal의 userName;
    public String getUserName(){
        return user.getUserName();
    }

    public User getUser(){
        return user;
    }

}
