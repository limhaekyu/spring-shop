package com.example.springshop.process.domain.product.dto;

import com.example.springshop.process.domain.product.domain.Product;
import com.example.springshop.process.domain.productImage.domain.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductInfoDto {
    Product product;
    List<ProductImage> productImage;
}
