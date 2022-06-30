package com.example.springshop.process.domain.wish.service;

import com.example.springshop.process.domain.product.domain.Product;
import com.example.springshop.process.domain.product.repository.ProductRepository;
import com.example.springshop.process.domain.product.service.ProductService;
import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.repository.UserRepository;
import com.example.springshop.process.domain.user.service.UserService;
import com.example.springshop.process.domain.wish.domain.Wish;
import com.example.springshop.process.domain.wish.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;
    private final UserService userService;
    private final ProductService productService;

    public void addWish(Long id, Long productId){
        User user = userService.findUserById(id);
        Product product = productService.findProductByid(productId);
        if (wishRepository.existsByUserAndProduct(user, product)){
            System.out.println("상품이 중복됩니다.");
        } else{
            Wish wish = new Wish(user, product);
            wishRepository.save(wish);
        }

    }

    public List<Wish> findUserWishList(Long id) {
        User user = userService.findUserById(id);
        return null;
    }
}
