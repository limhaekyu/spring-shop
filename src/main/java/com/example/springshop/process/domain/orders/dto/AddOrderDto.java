package com.example.springshop.process.domain.orders.dto;

import com.example.springshop.process.domain.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddOrderDto {
    private String deliveryAddress;

}
