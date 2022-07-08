package com.example.springshop.process.domain.product.service;

import com.example.springshop.process.domain.product.dto.CreateProductDto;
import com.example.springshop.process.domain.product.repository.ProductRepository;
import com.example.springshop.process.domain.product.domain.Product;
import com.example.springshop.process.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final UserService userSerivce;

    public void createProduct(Long userId, CreateProductDto createProductDto){
        Product product = new Product(createProductDto.getProductName(), createProductDto.getCategory(), createProductDto.getProductPrice(), userSerivce.findUserById(userId));

        productRepository.save(product);

    }

    public void productAddLike(Long productId){
        Product product = findProductByid(productId);
        product.productAddLike();

    }

    public void productCencelLike(Long productId) {
        Product product = findProductByid(productId);
        product.productCancelLike();
    }

    public Product findProductByid(Long productId){
        return productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("상품이 없습니다."));
    }
}
