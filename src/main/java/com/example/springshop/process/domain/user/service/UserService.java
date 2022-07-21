package com.example.springshop.process.domain.user.service;

import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.dto.request.*;
import com.example.springshop.process.domain.user.dto.response.FindUserEmailResponseDto;
import com.example.springshop.process.domain.user.dto.response.FindUserPasswordResponseDto;
import com.example.springshop.process.domain.user.repository.UserRepository;
import com.example.springshop.process.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;




    public void deleteUser(Long userId) {
        userRepository.delete(findUserById(userId));
    }

    public void updateUserInfo(Long userId, UpdateUserInfoDto updateUserDto) {
        User user = findUserById(userId);
        user.updateUserInfo(
                updateUserDto.getUserName(),
                updateUserDto.getPassword(),
                updateUserDto.getPhoneNumber()
        );
    }

    public User findUserById(Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("없는 유저입니다."));
        return user;
    }

    public void depositUserAccount(Long userId, DepositAmountDto depositAmountDto) {
        User user = findUserById(userId);
        user.depositUserAccount(user.getAccountAmount() + depositAmountDto.getDepositAmount());
    }

    public void orderPayment(Long buyerId, Long sellerId ,Long productPrice) {
        User buyer = findUserById(buyerId);
        User seller = findUserById(sellerId);

        if (buyer != seller){
            if (buyer.getAccountAmount() >= productPrice){
                buyer.orderPayment(buyer.getAccountAmount() - productPrice);
                seller.orderPayment(seller.getAccountAmount() + productPrice);
            }
        }
        userRepository.save(buyer);
        userRepository.save(seller);
    }

    public FindUserEmailResponseDto findUserEmail(FindUserEmailDto findUserEmailDto) {
        User user = userRepository.findByUserNameAndPhoneNumber(findUserEmailDto.getUserName(), findUserEmailDto.getPhoneNumber());
        return new FindUserEmailResponseDto(
                user.getEmail()
        );
    }

    public void userJoin(UserJoinDto userJoinDto) {
        if (!userRepository.existsByEmailOrPhoneNumber(userJoinDto.getEmail(), userJoinDto.getPhoneNumber())){
            userRepository.save(User.builder()
                .userName(userJoinDto.getUserName())
                .email(userJoinDto.getEmail())
                .password(passwordEncoder.encode(userJoinDto.getPassword()))
                .phoneNumber(userJoinDto.getPhoneNumber())
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER로 설정
                .build());
        } else{
            System.out.println("겹친다");
        }
    }

    public String userLogin(UserLoginDto userLoginDto) {
        User member = userRepository.findByEmail(userLoginDto.getEmail())
                .orElseThrow( () -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if(!passwordEncoder.matches(userLoginDto.getPassword(), member.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }


    public User findByEmailAndUserName(String userEmail, String userName) {
        return userRepository.findByEmailAndUserName(userEmail, userName);
    }
}
