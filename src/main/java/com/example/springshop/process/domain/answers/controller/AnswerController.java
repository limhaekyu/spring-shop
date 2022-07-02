package com.example.springshop.process.domain.answers.controller;

import com.example.springshop.process.domain.answers.domain.Answers;
import com.example.springshop.process.domain.answers.dto.CreateAnswerDto;
import com.example.springshop.process.domain.answers.dto.UpdateAnswerDto;
import com.example.springshop.process.domain.answers.service.AnswerService;
import com.example.springshop.process.global.response.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    //CRUD

    @PostMapping("/api/shop/{userId}/answer")
    public void createAnswer(@PathVariable Long userId, @RequestParam Long questionId, @RequestBody CreateAnswerDto createAnswerDto){
        answerService.createAnswer(userId, questionId, createAnswerDto);
    }

    @GetMapping("/api/shop/{userId}/answer")
    public ApiResponseDto<List<Answers>> selectUserAnswer(@PathVariable Long userId){
        List<Answers> userAnswerList = answerService.selectUserAnswer(userId);
        return ApiResponseDto.of(userAnswerList);
    }

    @GetMapping("/api/shop/answer")
    public ApiResponseDto<List<Answers>> selectAnswerToQuestion(@RequestParam Long questionId){
        List<Answers> answerToQuestionList = answerService.selectAnswerToQuestion(questionId);
        return ApiResponseDto.of(answerToQuestionList);
    }

    @PutMapping("/api/shop/{userId}/answer")
    public void updateAnswer(@PathVariable Long userId, @RequestParam Long answerId, @RequestBody UpdateAnswerDto updateAnswerDto){
        answerService.updateAnswer(userId, answerId, updateAnswerDto);
    }
}
