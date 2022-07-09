package com.example.springshop.process.domain.product.controller;

import com.example.springshop.process.domain.product.domain.Product;
import com.example.springshop.process.domain.product.dto.CreateProductDto;
import com.example.springshop.process.domain.product.dto.ProductInfoDto;
import com.example.springshop.process.domain.product.service.ProductService;
import com.example.springshop.process.global.response.ApiResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // CRUD
    @PostMapping("/api/shop/{userId}/product")
    public void createProduct(@PathVariable Long userId, @RequestBody @Valid CreateProductDto createProductDto){
        productService.createProduct(userId, createProductDto);
    }

    @GetMapping("/api/shop/product")
    public ApiResponseDto<ProductInfoDto> selectProductInfo(@RequestParam Long productId){
        ProductInfoDto productInfoDto = productService.selectProductInfo(productId);
        return ApiResponseDto.of(productInfoDto);
    }



}
