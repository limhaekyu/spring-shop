package com.example.springshop.domain.productReviewImage.domain;

import com.example.springshop.domain.productReview.domain.ProductReview;
import com.example.springshop.domain.user.domain.User;
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
    private Long id;

    private String originalFileName;

    private String changedFileName;

    private String fileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_review_id")
    @JsonManagedReference
    private ProductReview productReview;

}
