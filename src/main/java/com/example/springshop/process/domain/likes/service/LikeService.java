package com.example.springshop.process.domain.likes.service;

import com.example.springshop.process.domain.likes.domain.Likes;
import com.example.springshop.process.domain.likes.repository.LikeRepository;
import com.example.springshop.process.domain.product.domain.Product;
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

    public void addLike(Long productId, Long userId){
        Product product = new Product();
        product = productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("없는 상품입니다."));

        User user = new User();
        user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("없는 유저입니다."));

        Likes like = new Likes(product, user);

        likeRepository.save(like);

    }
}
