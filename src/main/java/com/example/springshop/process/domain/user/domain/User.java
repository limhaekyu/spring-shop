package com.example.springshop.process.domain.user.domain;

import com.example.springshop.process.domain.answers.domain.Answers;
import com.example.springshop.process.domain.likes.domain.Likes;
import com.example.springshop.process.domain.orders.domain.Orders;
import com.example.springshop.process.domain.product.domain.Product;
import com.example.springshop.process.domain.productReview.domain.ProductReview;
import com.example.springshop.process.domain.questions.domain.Questions;
import com.example.springshop.process.domain.wish.domain.Wish;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "user_account")
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
    private List<Likes> like = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Orders> order = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Questions> question = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Answers> answer = new ArrayList<>();

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email= email;
        this.password = password;
    }

    public void updateUserInfo(String userName, String passowrd){
        this.userName = userName;
        this.password = passowrd;
    }




}
