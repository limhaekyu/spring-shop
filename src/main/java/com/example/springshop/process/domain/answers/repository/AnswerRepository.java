package com.example.springshop.process.domain.answers.repository;

import com.example.springshop.process.domain.answers.domain.Answers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answers, Long> {
}
