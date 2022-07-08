package com.example.springshop.process.domain.productReview.service;

import com.example.springshop.process.domain.product.domain.Product;
import com.example.springshop.process.domain.product.service.ProductService;
import com.example.springshop.process.domain.orders.service.OrderService;
import com.example.springshop.process.domain.productReview.domain.ProductReview;
import com.example.springshop.process.domain.productReview.dto.AddProductReviewDto;
import com.example.springshop.process.domain.productReview.dto.UpdateProductReviewDto;
import com.example.springshop.process.domain.productReview.repository.ProductReviewRepository;
import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductReviewService {
    private final ProductReviewRepository productReviewRepository;
    private final ProductService productService;
    private final UserService userService;
    private final OrderService orderService;

    public void addProductReview(Long userId, Long productId, AddProductReviewDto addProductReviewDto) {
        User user = userService.findUserById(userId);
        Product product = productService.findProductByid(productId);
        Boolean checkUserOrdersForProduct = orderService.checkUserOrderForProduct(user,product);

        if (checkUserOrdersForProduct){
            ProductReview productReview = new ProductReview(addProductReviewDto.getProductReviewTitle(), addProductReviewDto.getProductReviewContents(), user, product);
            productReviewRepository.save(productReview);
        } else{
            System.out.println("주문하지 않은 사용자");
        }

    }

    public List<ProductReview> selectUserReview(Long userId) {
        User user = userService.findUserById(userId);
        List<ProductReview> productReviewList = productReviewRepository.findAllByUser(user);
        return productReviewList;
    }

    public List<ProductReview> selectProductReview(Long productId) {
        Product product = productService.findProductByid(productId);
        List<ProductReview> productReviewList = productReviewRepository.findAllByProduct(product);
        return productReviewList;
    }

    public void updateProductReview(Long userId, Long productReviewId, UpdateProductReviewDto updateProductReviewDto) {
        ProductReview productReview = findProductById(productReviewId);
        if(productReview.getUser().getId() == userId){
            productReview.updateProductReview(
                    updateProductReviewDto.getProductReviewTitle(),
                    updateProductReviewDto.getProductReviewContents());
        }
    }

    public ProductReview findProductById(Long productReviewId){
        return productReviewRepository.findById(productReviewId).orElseThrow(
                () -> new IllegalArgumentException("없는 리뷰입니다.")
        );
    }

    public void deleteProductReview(Long userId, Long productReviewId) {
        ProductReview productReview = findProductById(productReviewId);
        if (productReview.getUser().getId() == userId){
            productReviewRepository.delete(productReview);
        }
    }
}
