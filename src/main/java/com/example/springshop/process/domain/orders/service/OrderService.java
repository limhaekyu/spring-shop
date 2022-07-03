package com.example.springshop.process.domain.orders.service;

import com.example.springshop.process.domain.orders.domain.Orders;
import com.example.springshop.process.domain.orders.dto.AddOrderDto;
import com.example.springshop.process.domain.orders.repository.OrderRepository;
import com.example.springshop.process.domain.product.domain.Product;
import com.example.springshop.process.domain.product.service.ProductService;
import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<Orders> findUserOrder(Long userId) {
        User user = userService.findUserById(userId);
        List<Orders> orderList = orderRepository.findAllByUser(user);
        return orderList;
    }

    public void cancelUserOrder(Long userId, Long orderId) {
        User user = userService.findUserById(userId);
        Orders order = orderRepository.findByIdAndUser(orderId, user);
        if (user == order.getUser()) {
            orderRepository.delete(order);
        }
    }
}
