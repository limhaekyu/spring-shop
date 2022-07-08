package com.example.springshop.process.domain.productImage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateProductImageDto {
    private String productOriginalImageName;

    private String productChangedImageName;

    private String productFileUrl;
}
