package com.example.springshop.process.domain.wish.repository;


import com.example.springshop.process.domain.product.domain.Product;
import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.wish.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Long> {
    boolean existsByUserAndProduct(User user, Product product);
}
