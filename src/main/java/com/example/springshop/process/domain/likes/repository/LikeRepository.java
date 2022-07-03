package com.example.springshop.process.domain.likes.repository;

import com.example.springshop.process.domain.likes.domain.Likes;
import com.example.springshop.process.domain.orders.product.domain.Product;
import com.example.springshop.process.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    public Likes findByProductAndUser(Product product, User user);

    List<Likes> findAllByUser(User user);
}
