package com.example.springshop.process.domain.productReview.controller;

import com.example.springshop.process.domain.productReview.domain.ProductReview;
import com.example.springshop.process.domain.productReview.dto.AddProductReviewDto;
import com.example.springshop.process.domain.productReview.dto.UpdateProductReviewDto;
import com.example.springshop.process.domain.productReview.service.ProductReviewService;
import com.example.springshop.process.global.response.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductReviewController {
    private final ProductReviewService productReviewService;
    // UD

    // 구매한 사람들만 달 수 있게
    @PostMapping("/api/shop/{userId}/product-review")
    public void addProductReview(@PathVariable Long userId, @RequestParam Long productId, @RequestBody AddProductReviewDto addProductReviewDto) {
        productReviewService.addProductReview(userId, productId, addProductReviewDto);
    }

    @GetMapping("/api/shop/{userId}/product-review")
    public ApiResponseDto<ProductReview> selectUserReview(@PathVariable Long userId){
        List<ProductReview> userProductReviewList = productReviewService.selectUserReview(userId);
        return ApiResponseDto.of(userProductReviewList);
    }

    @GetMapping("/api/shop/product-review")
    public ApiResponseDto<ProductReview> selectProductReview(@RequestParam Long productId){
        List<ProductReview> productReviewList = productReviewService.selectProductReview(productId);
        return ApiResponseDto.of(productReviewList);
    }

    @PutMapping("/api/shop/{userId}/product-review")
    public void updateProductReview(@PathVariable Long userId, @RequestParam Long productReviewId, @RequestBody UpdateProductReviewDto updateProductReviewDto){
        productReviewService.updateProductReview(userId, productReviewId, updateProductReviewDto);
    }

    @DeleteMapping("/api/shop/{userId}/product-review")
    public void deleteProductReview(@PathVariable Long userId, @RequestParam Long productReviewId){
        productReviewService.deleteProductReview(userId, productReviewId);
    }

}

