package com.example.springshop.process.domain.productImage.repository;

import com.example.springshop.process.domain.product.domain.Product;
import com.example.springshop.process.domain.productImage.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    ProductImage findByProduct(Product product);

    List<ProductImage> findAllByProduct(Product product);
}
