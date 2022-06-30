package com.example.springshop.process.domain.wish.service;

import com.example.springshop.process.domain.product.domain.Product;
import com.example.springshop.process.domain.product.repository.ProductRepository;
import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.repository.UserRepository;
import com.example.springshop.process.domain.wish.domain.Wish;
import com.example.springshop.process.domain.wish.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public void addWish(Long id, Long productId){
        User user = userRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("없는 유저입니다."));
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("없는 상품입니다."));
        if (wishRepository.existsByUserAndProduct(user, product)){
            System.out.println("상품이 중복됩니다.");
        } else{
            Wish wish = new Wish(user, product);
            wishRepository.save(wish);
        }

    }
}
