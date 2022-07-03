package com.example.springshop.process.domain.orders.controller;

import com.example.springshop.process.domain.orders.domain.Orders;
import com.example.springshop.process.domain.orders.dto.AddOrderDto;
import com.example.springshop.process.domain.orders.service.OrderService;
import com.example.springshop.process.global.response.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/api/shop/{userId}/order")
    public void addOrder(@PathVariable Long userId, @RequestParam Long productId, @RequestBody AddOrderDto addOrderDto){
        orderService.AddOrder(userId, productId, addOrderDto);
    }

    @GetMapping("/api/shop/{userId}/order")
    public ApiResponseDto<List<Orders>> selectUserOrder(@PathVariable Long userId){
        List<Orders> orderList = orderService.findUserOrder(userId);
        return ApiResponseDto.of(orderList);
    }
}
