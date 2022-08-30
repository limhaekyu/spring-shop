package com.example.springshop.process.domain.user.domain;

import com.example.springshop.process.domain.answers.domain.Answers;
import com.example.springshop.process.domain.likes.domain.Likes;
import com.example.springshop.process.domain.model.Role;
import com.example.springshop.process.domain.orders.domain.Orders;
import com.example.springshop.process.domain.product.domain.Product;
import com.example.springshop.process.domain.productReview.domain.ProductReview;
import com.example.springshop.process.domain.questions.domain.Questions;
import com.example.springshop.process.domain.wish.domain.Wish;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@DynamicInsert
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "account_amount")
    @ColumnDefault("0L")
    private Long accountAmount;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "append_date")
    private LocalDateTime appendDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

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

    public User(String userName, String email, String password, String phoneNumber) {
        this.userName = userName;
        this.email= email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public void updateUserInfo(String userName, String passowrd, String phoneNumber){
        this.userName = userName;
        this.password = passowrd;
        this.phoneNumber = phoneNumber;
    }

    public void depositUserAccount(Long accountAmount){
        this.accountAmount = accountAmount;
    }


    public void orderPayment(Long accountAmount) {
        this.accountAmount = accountAmount;
    }

    public void updateUserPw(String pw) {
        this.password = pw;
    }

    @Builder
    public User(String password, Role role, LocalDateTime appendDate, LocalDateTime updateDate){
        this.password = password;
        this.role = role;
        this.appendDate = appendDate;
        this.updateDate = updateDate;
    }
}
