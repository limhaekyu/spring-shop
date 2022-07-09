package com.example.springshop.process.domain.productReviewImage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateProductReviewImageDto {
    private String reviewOriginalImageName;

    private String reviewChangedImageName;

    private String reviewImageUrl;
}
