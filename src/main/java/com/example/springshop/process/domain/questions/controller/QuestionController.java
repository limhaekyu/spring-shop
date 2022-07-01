package com.example.springshop.process.domain.questions.controller;

import com.example.springshop.process.domain.questions.domain.Questions;
import com.example.springshop.process.domain.questions.dto.AddQuestionDto;
import com.example.springshop.process.domain.questions.service.QuestionService;
import com.example.springshop.process.global.response.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    // CRUD

    @PostMapping("/api/shop/{id}/question")
    public void addQuestion(@PathVariable Long id, @RequestBody AddQuestionDto addQuestionDto){
        questionService.addQuestion(id, addQuestionDto);
    }

    @GetMapping("api/shop/question")
    public ApiResponseDto<List<Questions>> selectAllQuestion(){
        List<Questions> questionList = questionService.selectAllQuestion();
        return ApiResponseDto.of(questionList);
    }
}
