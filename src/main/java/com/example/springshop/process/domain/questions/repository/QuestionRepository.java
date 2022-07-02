package com.example.springshop.process.domain.questions.repository;

import com.example.springshop.process.domain.questions.domain.Questions;
import com.example.springshop.process.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Questions, Long> {
    List<Questions> findAllByUser(User user);
}
