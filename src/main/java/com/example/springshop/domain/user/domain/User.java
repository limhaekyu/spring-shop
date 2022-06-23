package com.example.springshop.domain.user.domain;

import com.example.springshop.domain.answer.domain.Answer;
import com.example.springshop.domain.like.domain.Like;
import com.example.springshop.domain.order.domain.Order;
import com.example.springshop.domain.product.domain.Product;
import com.example.springshop.domain.productReview.domain.ProductReview;
import com.example.springshop.domain.question.domain.Question;
import com.example.springshop.domain.wish.domain.Wish;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "user")
public class User {
    @Id
    private Long id;

    private String userName;

    private String password;

    private Long userAccount;

    @JsonBackReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> product = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductReview> productReview = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Wish> wish = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Like> like = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> order = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Question> question = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Answer> answer = new ArrayList<>();





}
