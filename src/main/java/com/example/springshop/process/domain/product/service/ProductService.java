package com.example.springshop.process.domain.product.service;

import com.example.springshop.process.domain.product.domain.Product;
import com.example.springshop.process.domain.product.dto.CreateProductDto;
import com.example.springshop.process.domain.product.repository.ProductRepository;
import com.example.springshop.process.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public void createProduct(Long userId, CreateProductDto createProductDto){
        Product product = new Product(createProductDto.getProductName(), createProductDto.getCategory(), userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("없는 유저입니다.")
        ));

        productRepository.save(product);

    }
}
