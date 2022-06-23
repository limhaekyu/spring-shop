package com.example.springshop.domain.product.domain;

import com.example.springshop.domain.like.domain.Like;
import com.example.springshop.domain.order.domain.Order;
import com.example.springshop.domain.productImage.domain.ProductImage;
import com.example.springshop.domain.productReview.domain.ProductReview;
import com.example.springshop.domain.user.domain.User;
import com.example.springshop.domain.wish.domain.Wish;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "product")
public class Product {

    @Id
    private Long id;

    private String productName;

    private Enum category;

    private int likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @JsonBackReference
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductReview> productReview = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Wish> wish = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Like> like = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> order = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductImage> productImage = new ArrayList<>();


}
