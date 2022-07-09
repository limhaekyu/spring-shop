package com.example.springshop.process.domain.product.dto;

import com.example.springshop.process.domain.model.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateProductInfoDto {
    private String productName;

    private CategoryType category;

    private Long productPrice;

}
