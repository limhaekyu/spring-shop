package com.example.springshop.process.domain.productReview.repository;

import com.example.springshop.process.domain.productReview.domain.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
}
