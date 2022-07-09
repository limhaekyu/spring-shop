package com.example.springshop.process.domain.product.domain;

import com.example.springshop.process.domain.likes.domain.Likes;
import com.example.springshop.process.domain.model.CategoryType;
import com.example.springshop.process.domain.orders.domain.Orders;
import com.example.springshop.process.domain.productImage.domain.ProductImage;
import com.example.springshop.process.domain.productReview.domain.ProductReview;
import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.wish.domain.Wish;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "product")
@DynamicInsert
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "category")
    private CategoryType category;

    @Column(name = "product_price")
    private Long productPrice;

    @Column(name = "like_count")
    @ColumnDefault("0")
    private Integer likeCount;

    @Column(name = "click_count")
    @ColumnDefault("0")
    private Integer clickCount;

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
    private List<Likes> like = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Orders> order = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductImage> productImage = new ArrayList<>();


    public Product(String productName, CategoryType category,Long productPrice, User user) {
        this.productName = productName;
        this.category = category;
        this.productPrice = productPrice;
        this.user = user;
    }

    public void productAddLike(){
        this.likeCount += 1;
    }

    public void productCancelLike() {
        this.likeCount -= 1;
    }

    public void updateProductInfo(String productName, CategoryType category, Long productPrice) {
        this.productName = productName;
        this.category = category;
        this.productPrice = productPrice;
    }
}
