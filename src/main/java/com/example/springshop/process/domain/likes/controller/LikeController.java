package com.example.springshop.process.domain.likes.controller;

import com.example.springshop.process.domain.likes.domain.Likes;
import com.example.springshop.process.domain.likes.service.LikeService;
import com.example.springshop.process.global.response.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/api/shop/{userId}/like")
    public void addLike(@PathVariable Long userId, @RequestParam Long productId){
        likeService.addLike(userId, productId);
    }

    @DeleteMapping("/api/shop/{userId}/like")
    public void cancelLike(@PathVariable Long userId, @RequestParam Long productId){
        likeService.cancelLike(userId, productId);
    }

    @GetMapping("/api/shop/{userId}/like")
    public ApiResponseDto<List<Likes>> selectUserLike(@PathVariable Long userId){
        List<Likes> likeList = likeService.selectUserLike(userId);
        return ApiResponseDto.of(likeList);
    }
}
