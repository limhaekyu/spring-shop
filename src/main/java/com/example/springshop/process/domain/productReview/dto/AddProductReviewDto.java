package com.example.springshop.process.domain.productReview.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddProductReviewDto {
    private String productReviewTitle;

    private String productReviewContents;

}
