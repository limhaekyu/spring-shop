package com.example.springshop.process.domain.user.repository;

import com.example.springshop.process.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserNameAndPhoneNumber(String userName, String phoneNumber);
}
