package com.example.springshop.process.domain.productReview.domain;

import com.example.springshop.process.domain.product.domain.Product;
import com.example.springshop.process.domain.productReviewImage.domain.ProductReviewImage;
import com.example.springshop.process.domain.user.domain.User;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_review_id")
    private Long id;

    @Column(name = "product_review_title")
    private String productReviewTitle;

    @Column(name = "product_review_contents")
    private String productReviewContents;

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
