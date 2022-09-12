package com.example.springshop.process.domain.user.service;

import com.auth0.jwt.JWT;
import com.example.springshop.process.domain.model.Role;
import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.dto.request.*;
import com.example.springshop.process.domain.user.dto.response.FindUserEmailResponseDto;
import com.example.springshop.process.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
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
                .email(userJoinDto.getEmail())
                .password(passwordEncoder.encode(userJoinDto.getPassword()))
                .userName(userJoinDto.getUserName())
                .phoneNumber(userJoinDto.getPhoneNumber())
                .role(Role.USER) // 최초 가입시 USER로 설정
                .build());
        } else if(userRepository.existsByEmail(userJoinDto.getEmail())){
            System.out.println("이미 사용중인 이메일입니다.");
        } else if(userRepository.existsByPhoneNumber(userJoinDto.getPhoneNumber())){
            System.out.println("이미 사용중인 전화번호입니다.");
        }
    }

    public void userLogin(UserLoginRequestDto userLoginDto) {
        User member = userRepository.findByEmail(userLoginDto.getEmail())
                .orElseThrow( () -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if(!passwordEncoder.matches(userLoginDto.getPassword(), member.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
    }


    public User findByEmailAndUserName(String userEmail, String userName) {
        return userRepository.findByEmailAndUserName(userEmail, userName);
    }
}
