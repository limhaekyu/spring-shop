package com.example.springshop.process.domain.productReviewImage.domain;

import com.example.springshop.process.domain.productReview.domain.ProductReview;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_review_image")
public class ProductReviewImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_review_image_id")
    private Long id;

    @Column(name = "review_original_image_name")
    private String reviewOriginalImageName;

    @Column(name = "review_changed_image_name")
    private String reviewChangedImageName;

    @Column(name = "review_image_url")
    private String reviewImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_review_id")
    @JsonManagedReference
    private ProductReview productReview;

}
