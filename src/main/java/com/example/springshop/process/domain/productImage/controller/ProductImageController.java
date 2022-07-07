package com.example.springshop.process.domain.productImage.controller;

import com.example.springshop.process.domain.productImage.dto.UploadProductImageDto;
import com.example.springshop.process.domain.productImage.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;

    @PostMapping("/api/shop/product-image")
    public void uploadProductImage(@RequestParam Long productId, @RequestBody UploadProductImageDto uploadProductImageDto){
        productImageService.uploadProductImage(productId, uploadProductImageDto);
    }
}
