package com.example.springshop.process.domain.likes.service;

import com.example.springshop.process.domain.likes.domain.Likes;
import com.example.springshop.process.domain.likes.repository.LikeRepository;
import com.example.springshop.process.domain.product.domain.Product;
import com.example.springshop.process.domain.product.repository.ProductRepository;
import com.example.springshop.process.domain.product.service.ProductService;
import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    public void addLike(Long productId, Long userId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("없는 상품입니다."));

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("없는 유저입니다."));

        Likes like = new Likes(product, user);

        productService.productAddLike(productId);
        likeRepository.save(like);

    }

    public void cancelLike(Long productId, Long userId) {

        Likes like = likeRepository.findByProductAndUser(
                productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("없는 상품입니다.")),
                userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("없는 유저입니다.")));

        productService.productCencelLike(productId);
        likeRepository.delete(like);
    }

    public List<Likes> selectUserLike(Long userId) {
        List<Likes> userLikesList = likeRepository.findAllByUser(
                userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("없는 유저입니다.")));
        return userLikesList;
    }
}