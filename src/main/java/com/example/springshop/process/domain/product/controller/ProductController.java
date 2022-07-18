package com.example.springshop.process.domain.product.controller;

import com.example.springshop.process.domain.model.CategoryType;
import com.example.springshop.process.domain.product.dto.CreateProductDto;
import com.example.springshop.process.domain.product.dto.ProductInfoDto;
import com.example.springshop.process.domain.product.dto.UpdateProductInfoDto;
import com.example.springshop.process.domain.product.dto.response.CategoryRankingResponseDto;
import com.example.springshop.process.domain.product.service.ProductService;
import com.example.springshop.process.global.response.ApiResponseDto;
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

    @PutMapping("/api/shop/product")
    public void updateProductInfo(@RequestParam Long productId, @RequestBody UpdateProductInfoDto updateProductInfoDto){
        productService.updateProductInfo(productId, updateProductInfoDto);
    }

    @DeleteMapping("/api/shop/product")
    public void deleteProduct(@RequestParam Long productId){
        productService.deleteProduct(productId);
    }

    @GetMapping("/api/shop/category-ranking")
    public ApiResponseDto<CategoryRankingResponseDto> findCategoryRanking(@RequestParam CategoryType categoryType){
        CategoryRankingResponseDto categoryRankingResponseDto = productService.findCategoryRanking(categoryType);
        return ApiResponseDto.of(categoryRankingResponseDto);
    }
}
