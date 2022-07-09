package com.example.springshop.process.domain.productReviewImage.controller;

import com.example.springshop.process.domain.productReview.dto.UpdateProductReviewDto;
import com.example.springshop.process.domain.productReviewImage.dto.UpdateProductReviewImageDto;
import com.example.springshop.process.domain.productReviewImage.dto.UploadProductReviewImageDto;
import com.example.springshop.process.domain.productReviewImage.service.ProductReviewImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductReviewImageController {
    private final ProductReviewImageService productReviewImageService;

    @PostMapping("/api/shop/product-review-image")
    public void uploadProductReviewImage(@RequestParam Long productReviewId, @RequestBody UploadProductReviewImageDto uploadProductReviewImageDto){
        productReviewImageService.uploadProductReviewImage(productReviewId, uploadProductReviewImageDto);
    }

    @PutMapping("/api/shop/product-review-image")
    public void updateProductReviewImage(@RequestParam Long productReviewImageId, @RequestBody UpdateProductReviewImageDto updateProductReviewImageDto){
        productReviewImageService.updateProductReviewImage(productReviewImageId, updateProductReviewImageDto);
    }
}
