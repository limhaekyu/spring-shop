package com.example.springshop.domain.productImage.domain;

import com.example.springshop.domain.product.domain.Product;
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
    private Long id;

    private String originalFileName;

    private String changedFileName;

    private String fileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonManagedReference
    private Product product;

}
