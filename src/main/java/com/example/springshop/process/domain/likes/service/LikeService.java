package com.example.springshop.process.domain.likes.service;

import com.example.springshop.process.domain.likes.domain.Likes;
import com.example.springshop.process.domain.likes.repository.LikeRepository;
import com.example.springshop.process.domain.product.domain.Product;
import com.example.springshop.process.domain.product.repository.ProductRepository;
import com.example.springshop.process.domain.product.service.ProductService;
import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.repository.UserRepository;
import com.example.springshop.process.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final ProductService productService;
    private final UserService userService;

    public void addLike(Long productId, Long userId) {
        Product product = productService.findProductByid(productId);
        User user = userService.findUserById(userId);

        Likes like = new Likes(product, user);

        productService.productAddLike(productId);
        likeRepository.save(like);

    }

    public void cancelLike(Long productId, Long userId) {

        Likes like = likeRepository.findByProductAndUser(
                productService.findProductByid(productId),
                userService.findUserById(userId));

        productService.productCencelLike(productId);
        likeRepository.delete(like);
    }

    public List<Likes> selectUserLike(Long userId) {
        List<Likes> userLikesList = likeRepository.findAllByUser(
                userService.findUserById(userId));
        return userLikesList;
    }
}