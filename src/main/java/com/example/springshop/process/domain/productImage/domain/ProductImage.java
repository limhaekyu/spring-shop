package com.example.springshop.process.domain.productImage.domain;

import com.example.springshop.process.domain.orders.product.domain.Product;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "product_image")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_image_id")
    private Long id;

    @Column(name = "product_original_image_name")
    private String productOriginalImageName;

    @Column(name = "product_changed_image_name")
    private String productChangedImageName;

    @Column(name = "product_file_url")
    private String productFileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonManagedReference
    private Product product;

    public ProductImage(String productOriginalImageName, String productChangedImageName, String productFileUrl, Product product) {
        this.productOriginalImageName = productOriginalImageName;
        this.productChangedImageName = productChangedImageName;
        this.productFileUrl = productFileUrl;
        this.product = product;
    }

    public void updateProductImage(String productOriginalImageName, String productChangedImageName, String productFileUrl) {
        this.productOriginalImageName = productOriginalImageName;
        this.productChangedImageName = productChangedImageName;
        this.productFileUrl = productFileUrl;
    }
}
