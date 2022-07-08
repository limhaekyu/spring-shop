package com.example.springshop.process.domain.orders.domain;

import com.example.springshop.process.domain.model.OrderStatus;
import com.example.springshop.process.domain.product.domain.Product;
import com.example.springshop.process.domain.user.domain.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "status")
    private OrderStatus status = OrderStatus.ORDER_CHECK;

    @Column(name = "ordered_at")
    private LocalDateTime orderedAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonManagedReference
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    public Orders(String deliveryAddress, Product product, User user) {
        this.deliveryAddress = deliveryAddress;
        this.status = status;
        this.product = product;
        this.user = user;
    }
}
