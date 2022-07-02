package com.example.springshop.process.domain.questions.controller;

import com.example.springshop.process.domain.questions.domain.Questions;
import com.example.springshop.process.domain.questions.dto.AddQuestionDto;
import com.example.springshop.process.domain.questions.dto.UpdateQuestionDto;
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

    @PostMapping("/api/shop/{userId}/question")
    public void addQuestion(@PathVariable Long userId, @RequestBody AddQuestionDto addQuestionDto){
        questionService.addQuestion(userId, addQuestionDto);
    }

    @GetMapping("api/shop/question")
    public ApiResponseDto<List<Questions>> selectAllQuestion(){
        List<Questions> allQuestionList = questionService.selectAllQuestion();
        return ApiResponseDto.of(allQuestionList);
    }

    @GetMapping("api/shop/{userId}/question")
    public ApiResponseDto<List<Questions>> selectUserQuestion(@PathVariable Long userId){
        List<Questions> userQuestionList = questionService.selectUserQuestion(userId);
        return ApiResponseDto.of(userQuestionList);
    }

    @PutMapping("api/shop/{userId}/question")
    public void updateUserQuestion(@PathVariable Long userId ,@RequestParam Long questionId , @RequestBody UpdateQuestionDto updateQuestionDto){
        questionService.updateQuestion(userId, questionId, updateQuestionDto);

    }


}
