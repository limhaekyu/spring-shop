package com.example.springshop.process.domain.likes.service;

import com.example.springshop.process.domain.likes.domain.Likes;
import com.example.springshop.process.domain.likes.repository.LikeRepository;
import com.example.springshop.process.domain.product.repository.ProductRepository;
import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public void addLike(Long userId, Long productId){

    }
}
