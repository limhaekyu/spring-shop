package com.example.springshop.process.domain.productImage.service;

import com.example.springshop.process.domain.orders.product.domain.Product;
import com.example.springshop.process.domain.orders.product.service.ProductService;
import com.example.springshop.process.domain.productImage.domain.ProductImage;
import com.example.springshop.process.domain.productImage.dto.UploadProductImageDto;
import com.example.springshop.process.domain.productImage.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductImageService {
    private final ProductImageRepository productImageRepository;
    private final ProductService productService;

    public void uploadProductImage(Long productId, UploadProductImageDto uploadProductImageDto) {
        Product product = productService.findProductByid(productId);
        ProductImage productImage = new ProductImage(
                uploadProductImageDto.getProductOriginalImageName(),
                uploadProductImageDto.getProductChangedImageName(),
                uploadProductImageDto.getProductFileUrl(),
                product
        );
        productImageRepository.save(productImage);
    }
}
