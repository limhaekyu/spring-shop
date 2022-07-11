package com.example.springshop.process.domain.product.dto.response;

import com.example.springshop.process.domain.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryRankingResponseDto {
    private List<Product> productList;
}
