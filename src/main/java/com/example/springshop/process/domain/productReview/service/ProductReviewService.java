package com.example.springshop.process.domain.productReview.service;

import com.example.springshop.process.domain.product.domain.Product;
import com.example.springshop.process.domain.product.service.ProductService;
import com.example.springshop.process.domain.productReview.dto.AddProductReviewDto;
import com.example.springshop.process.domain.productReview.repository.ProductReviewRepository;
import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductReviewService {
    private final ProductReviewRepository productReviewRepository;
    private final ProductService productService;
    private final UserService userService;


    public void addProductReview(Long userId, Long productId, AddProductReviewDto addProductReviewDto) {
        User user = userService.findUserById(userId);
        Product product = productService.findProductByid(productId);


    }
}
