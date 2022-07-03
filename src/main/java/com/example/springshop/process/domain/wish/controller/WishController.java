package com.example.springshop.process.domain.wish.controller;

import com.example.springshop.process.domain.wish.domain.Wish;
import com.example.springshop.process.domain.wish.service.WishService;
import com.example.springshop.process.global.response.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;
//    CRUD
//    C : 위시리스트 등록하기
//    R : 위시리스트 불러오기
//    U : X
//    D : 위시리스트 삭제
    @PostMapping("/api/shop/{userId}/wish")
    public void addWish(@PathVariable Long userId, @RequestParam Long productId){
        wishService.addWish(userId, productId);
    }

    @GetMapping("/api/shop/{userId}/wish")
    public ApiResponseDto<List<Wish>> findUserWishList(@PathVariable Long userId){
        List<Wish> wishList = wishService.findUserWishList(userId);
        return ApiResponseDto.of(wishList);
    }

    @DeleteMapping("/api/shop/{userId}/wish")
    public void deleteWish(@PathVariable Long userId, @RequestParam Long productId){
        wishService.deleteWish(userId, productId);
    }
}
