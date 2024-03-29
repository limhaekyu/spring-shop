package com.example.springshop.process.domain.user.repository;

import com.example.springshop.process.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserNameAndPhoneNumber(String userName, String phoneNumber);

    Optional<User> findByEmail(String username);

    Boolean existsByEmailOrPhoneNumber(String email, String phoneNumber);

    User findByEmailAndUserName(String email, String userName);

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String phoneNumber);
}
