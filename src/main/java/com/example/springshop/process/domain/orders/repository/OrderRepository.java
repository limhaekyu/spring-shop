package com.example.springshop.process.domain.orders.repository;

import com.example.springshop.process.domain.orders.domain.Orders;
import com.example.springshop.process.domain.orders.product.domain.Product;
import com.example.springshop.process.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByUser(User user);

    Orders findByIdAndUser(Long orderId, User user);

    Boolean existsByUserAndProduct(User user, Product product);
}
