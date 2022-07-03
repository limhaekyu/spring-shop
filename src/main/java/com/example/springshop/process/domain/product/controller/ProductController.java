package com.example.springshop.process.domain.product.controller;

import com.example.springshop.process.domain.product.dto.CreateProductDto;
import com.example.springshop.process.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
