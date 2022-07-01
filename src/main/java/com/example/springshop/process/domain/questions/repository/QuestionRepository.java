package com.example.springshop.process.domain.questions.repository;

import com.example.springshop.process.domain.questions.domain.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Questions, Long> {
}
