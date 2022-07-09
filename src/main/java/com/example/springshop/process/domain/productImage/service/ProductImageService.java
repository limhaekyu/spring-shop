package com.example.springshop.process.domain.productImage.service;

import com.example.springshop.process.domain.product.domain.Product;
import com.example.springshop.process.domain.product.service.ProductService;
import com.example.springshop.process.domain.productImage.domain.ProductImage;
import com.example.springshop.process.domain.productImage.dto.UpdateProductImageDto;
import com.example.springshop.process.domain.productImage.dto.UploadProductImageDto;
import com.example.springshop.process.domain.productImage.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void updateProductImage(Long productId, UpdateProductImageDto updateProductImageDto) {
        Product product = productService.findProductByid(productId);
        ProductImage productImage = findProductImageByProduct(product);
        productImage.updateProductImage(
                updateProductImageDto.getProductOriginalImageName(),
                updateProductImageDto.getProductChangedImageName(),
                updateProductImageDto.getProductFileUrl()
        );
        productImageRepository.save(productImage);

    }


    public void deleteProductImage(Long productId) {
        Product product = productService.findProductByid(productId);
        ProductImage productImage = findProductImageByProduct(product);
        productImageRepository.delete(productImage);
    }

    public ProductImage findProductImageByProduct(Product product) {
        ProductImage productImage = productImageRepository.findByProduct(product);
        return productImage;
    }

    public List<ProductImage> findAllProductImageByProduct(Product product) {
        List<ProductImage> productImageList = productImageRepository.findAllByProduct(product);
        return productImageList;
    }
}
