package com.example.springshop.process.domain.likes.repository;

import com.example.springshop.process.domain.likes.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes, Long> {
}
