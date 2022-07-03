package com.example.springshop.process.domain.orders.service;

import com.example.springshop.process.domain.orders.domain.Orders;
import com.example.springshop.process.domain.orders.dto.AddOrderDto;
import com.example.springshop.process.domain.orders.repository.OrderRepository;
import com.example.springshop.process.domain.product.domain.Product;
import com.example.springshop.process.domain.product.service.ProductService;
import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;


    public void AddOrder(Long userId, Long productId, AddOrderDto addOrderDto) {
        User user = userService.findUserById(userId);
        Product product = productService.findProductByid(productId);

        if (product.getUser() != user){
            Orders order = new Orders(addOrderDto.getDeliveryAddress(), product, user);

            userService.orderPayment(userId, product.getUser().getId() ,product.getProductPrice());
            orderRepository.save(order);
        }
    }
}
