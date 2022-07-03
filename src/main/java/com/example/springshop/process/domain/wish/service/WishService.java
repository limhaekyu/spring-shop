package com.example.springshop.process.domain.wish.service;

import com.example.springshop.process.domain.orders.product.domain.Product;
import com.example.springshop.process.domain.orders.product.service.ProductService;
import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.service.UserService;
import com.example.springshop.process.domain.wish.domain.Wish;
import com.example.springshop.process.domain.wish.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WishService {

    private final WishRepository wishRepository;
    private final UserService userService;
    private final ProductService productService;

    public void addWish(Long userId, Long productId){
        User user = userService.findUserById(userId);
        Product product = productService.findProductByid(productId);
        if (wishRepository.existsByUserAndProduct(user, product)){
            System.out.println("상품이 중복됩니다.");
        } else{
            Wish wish = new Wish(user, product);
            wishRepository.save(wish);
        }

    }

    public List<Wish> findUserWishList(Long userId) {
        User user = userService.findUserById(userId);
        List<Wish> wishList = wishRepository.findAllByUser(user);
        return wishList;
    }

    public void deleteWish(Long userId, Long productId) {
        User user = userService.findUserById(userId);
        Product product = productService.findProductByid(productId);
        Wish wish = wishRepository.findAllByUserAndProduct(user, product);

        if (wish != null){
            wishRepository.delete(wish);
        } else{
            System.out.println("해당 상품이 위시리스트에 존재하지 않습니다.");
        }

    }
}
