package com.example.springshop.domain.productReview.domain;

import com.example.springshop.domain.like.domain.Like;
import com.example.springshop.domain.product.domain.Product;
import com.example.springshop.domain.productReviewImage.domain.ProductReviewImage;
import com.example.springshop.domain.user.domain.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_review")
public class ProductReview {
    @Id
    private Long id;

    private String title;

    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonManagedReference
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @JsonBackReference
    @OneToMany(mappedBy = "productReview", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductReviewImage> productReviewImage = new ArrayList<>();


}
