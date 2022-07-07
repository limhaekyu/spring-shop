package com.example.springshop.process.domain.productImage.repository;

import com.example.springshop.process.domain.productImage.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
