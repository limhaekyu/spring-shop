package com.example.springshop.process.domain.productReviewImage.service;

import com.example.springshop.process.domain.productReview.domain.ProductReview;
import com.example.springshop.process.domain.productReview.dto.UpdateProductReviewDto;
import com.example.springshop.process.domain.productReview.service.ProductReviewService;
import com.example.springshop.process.domain.productReviewImage.domain.ProductReviewImage;
import com.example.springshop.process.domain.productReviewImage.dto.UpdateProductReviewImageDto;
import com.example.springshop.process.domain.productReviewImage.dto.UploadProductReviewImageDto;
import com.example.springshop.process.domain.productReviewImage.repository.ProductReviewImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductReviewImageService {
    private final ProductReviewImageRepository productReviewImageRepository;
    private final ProductReviewService productReviewService;

    public void uploadProductReviewImage(Long productReviewId, UploadProductReviewImageDto uploadProductReviewImageDto) {
        ProductReview productReview = productReviewService.findProductById(productReviewId);
        ProductReviewImage productReviewImage = new ProductReviewImage(
                uploadProductReviewImageDto.getReviewOriginalImageName(),
                uploadProductReviewImageDto.getReviewChangedImageName(),
                uploadProductReviewImageDto.getReviewImageUrl(),
                productReview
        );
        productReviewImageRepository.save(productReviewImage);
    }

    public ProductReviewImage findProductReviewImageById(Long ProductReviewImageId){
        ProductReviewImage productReviewImage = productReviewImageRepository.findById(ProductReviewImageId).orElseThrow(
                () -> new IllegalArgumentException("리뷰 이미지가 없습니다.")
        );
        return productReviewImage;
    }

    public void updateProductReviewImage(Long productReviewImageId, UpdateProductReviewImageDto updateProductReviewImageDto) {
        ProductReviewImage productReviewImage = findProductReviewImageById(productReviewImageId);
        productReviewImage.updateProductReviewImage(
                updateProductReviewImageDto.getReviewOriginalImageName(),
                updateProductReviewImageDto.getReviewChangedImageName(),
                updateProductReviewImageDto.getReviewImageUrl()
        );
        productReviewImageRepository.save(productReviewImage);

    }

    public void deleteProductReviewImage(Long productReviewImageId) {
        ProductReviewImage productReviewImage = findProductReviewImageById(productReviewImageId);
        productReviewImageRepository.delete(productReviewImage);
    }
}
