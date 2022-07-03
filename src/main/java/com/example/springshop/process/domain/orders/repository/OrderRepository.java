package com.example.springshop.process.domain.orders.repository;

import com.example.springshop.process.domain.orders.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
