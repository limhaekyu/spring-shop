package com.example.springshop.process.domain.user.controller;

import javax.validation.Valid;

import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.dto.request.*;
import com.example.springshop.process.domain.user.dto.response.FindUserEmailResponseDto;
import com.example.springshop.process.domain.user.service.UserService;
import com.example.springshop.process.global.response.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    // 회원가입
    @PostMapping("/join")
    public Long join(@RequestBody Map<String, String> user){
        return userService.userJoin(user);
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody UserLoginDto userLoginDto){
        return userService.userLogin(userLoginDto);
    }


    // CRUD
    @PostMapping("/api/shop/user")
    public void createtUser(@RequestBody @Valid CreateUserDto createUserDto){
        userService.createtUser(createUserDto);
    }

    @DeleteMapping("/api/shop/{userId}/user")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

    @PutMapping("/api/shop/{userId}/user")
    public void updateUser(@PathVariable Long userId, @RequestBody UpdateUserInfoDto updateUserDto){
        userService.updateUserInfo(userId, updateUserDto);
    }

    @GetMapping("/api/shop/{userId}/user")
    public ApiResponseDto<User> findUser(@PathVariable Long userId){
        User user = userService.findUserById(userId);
        return ApiResponseDto.of(user);
    }

    @PostMapping("/api/shop/{userId}/user/deposit")
    public void depositUserAccount(@PathVariable Long userId, @RequestBody DepositAmountDto depositAmountDto){
        userService.depositUserAccount(userId, depositAmountDto);

    }

    @GetMapping("/api/shop/find-user-email")
    public ApiResponseDto<FindUserEmailResponseDto> findUserEmail(@RequestBody FindUserEmailDto findUserEmailDto){
        FindUserEmailResponseDto findUserEmailResponseDto = userService.findUserEmail(findUserEmailDto);
        return ApiResponseDto.of(findUserEmailResponseDto);
    }

}
