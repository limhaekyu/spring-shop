package com.example.springshop.process.domain.user.service;

import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.dto.CreateUserDto;
import com.example.springshop.process.domain.user.dto.DepositAmountDto;
import com.example.springshop.process.domain.user.dto.UpdateUserInfoDto;
import com.example.springshop.process.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createtUser(CreateUserDto insertUserDto) {
        User user = new User(insertUserDto.getUserName(), insertUserDto.getEmail(), insertUserDto.getPassword());
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.delete(findUserById(userId));
    }

    public void updateUserInfo(Long userId, UpdateUserInfoDto updateUserDto) {
        User user = findUserById(userId);
        user.updateUserInfo(
                updateUserDto.getUserName(),
                updateUserDto.getPassword()
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
}
