package com.example.springshop.process.domain.questions.controller;

import com.example.springshop.process.domain.questions.dto.AddQuestionDto;
import com.example.springshop.process.domain.questions.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    // CRUD

    @PostMapping("/api/shop/{id}/question")
    public void addQuestion(@PathVariable Long id, @RequestBody AddQuestionDto addQuestionDto){
        questionService.addQuestion(id, addQuestionDto);
    }
}
