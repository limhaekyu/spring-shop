package com.example.springshop.process.domain.product.repository;

import com.example.springshop.process.domain.model.CategoryType;
import com.example.springshop.process.domain.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryOrderByLikeCountDesc(CategoryType categoryType);

}
