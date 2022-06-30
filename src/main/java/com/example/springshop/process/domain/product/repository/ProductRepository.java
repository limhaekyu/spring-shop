package com.example.springshop.process.domain.product.repository;

import com.example.springshop.process.domain.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}