package com.example.springshop.process.domain.productReview.repository;

import com.example.springshop.process.domain.orders.product.domain.Product;
import com.example.springshop.process.domain.productReview.domain.ProductReview;
import com.example.springshop.process.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
    List<ProductReview> findAllByUser(User user);

    List<ProductReview> findAllByProduct(Product product);
}
