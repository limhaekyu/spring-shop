package com.example.springshop.process.domain.productImage.controller;

import com.example.springshop.process.domain.productImage.dto.UpdateProductImageDto;
import com.example.springshop.process.domain.productImage.dto.UploadProductImageDto;
import com.example.springshop.process.domain.productImage.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;

    // 중복체크 필요
    @PostMapping("/api/shop/product-image")
    public void uploadProductImage(@RequestParam Long productId, @RequestBody UploadProductImageDto uploadProductImageDto){
        productImageService.uploadProductImage(productId, uploadProductImageDto);
    }

    @PutMapping("/api/shop/product-image")
    public void updateProductImage(@RequestParam Long productId, @RequestBody UpdateProductImageDto updateProductImageDto){
        productImageService.updateProductImage(productId, updateProductImageDto);
    }

    @DeleteMapping("/api/shop/product-image")
    public void deleteProductImage(@RequestParam Long productId){
        productImageService.deleteProductImage(productId);
    }

    
}
