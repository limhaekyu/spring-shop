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
    @PostMapping("/api/shop/{id}/wish")
    public void addWish(@PathVariable Long id, @RequestParam Long productId){
        wishService.addWish(id, productId);
    }

    @GetMapping("/api/shop/{id}/wish")
    public ApiResponseDto<List<Wish>> findUserWishList(@PathVariable Long id){
        List<Wish> wishList = wishService.findUserWishList(id);
        return ApiResponseDto.of(wishList);
    }
}
