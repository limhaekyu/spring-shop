package com.example.springshop.process.domain.answers.repository;

import com.example.springshop.process.domain.answers.domain.Answers;
import com.example.springshop.process.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answers, Long> {
    List<Answers> findAllByUser(User user);
}
