package com.example.springshop.process.domain.orders.controller;

import com.example.springshop.process.domain.orders.dto.AddOrderDto;
import com.example.springshop.process.domain.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/api/shop/{userId}/order")
    public void addOrder(@PathVariable Long userId, @RequestParam Long productId, @RequestBody AddOrderDto addOrderDto){
        orderService.AddOrder(userId, productId, addOrderDto);
    }
}
