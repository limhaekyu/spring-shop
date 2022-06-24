package com.example.springshop.process.domain.user.controller;

import javax.validation.Valid;

import com.example.springshop.process.domain.user.dto.CreateUserDto;
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

    @DeleteMapping("/api/shop/user")
    public void deleteUser(){

    }

    @PutMapping("/api/shop/user")
    public void updateUser(){

    }

    @GetMapping("/api/shop/user")
    public void findUser(){

    }
}
