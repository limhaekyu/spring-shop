package com.example.springshop.process.domain.likes.controller;

import com.example.springshop.process.domain.likes.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/api/shop/{id}/like")
    public void addLike(@PathVariable Long id, @RequestParam Long productId){
        likeService.addLike(productId, id);
    }

    @DeleteMapping("/api/shop/{id}/like")
    public void cancelLike(@PathVariable Long id, @RequestParam Long productId){
        likeService.cancelLike(productId, id);
    }
}
