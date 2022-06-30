package com.example.springshop.process.domain.user.controller;

import javax.validation.Valid;

import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.dto.CreateUserDto;
import com.example.springshop.process.domain.user.dto.DepositAmountDto;
import com.example.springshop.process.domain.user.dto.UpdateUserInfoDto;
import com.example.springshop.process.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // CRUD
    @PostMapping("/api/shop/user")
    public void createtUser(@RequestBody @Valid CreateUserDto createUserDto){
        userService.createtUser(createUserDto);
    }

    @DeleteMapping("/api/shop/{id}/user")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PutMapping("/api/shop/{id}/user")
    public void updateUser(@PathVariable Long id, @RequestBody UpdateUserInfoDto updateUserDto){
        userService.updateUserInfo(id, updateUserDto);
    }

    @GetMapping("/api/shop/{id}/user")
    public User findUser(@PathVariable Long id){
        return userService.findUserById(id);
    }

    @PostMapping("/api/shop/{id}/user/deposit")
    public void depositUserAccount(@PathVariable Long id, @RequestBody DepositAmountDto depositAmountDto){
        userService.depositUserAccount(id, depositAmountDto);

    }

}
