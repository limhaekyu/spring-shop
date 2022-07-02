package com.example.springshop.process.domain.answers.controller;

import com.example.springshop.process.domain.answers.domain.Answers;
import com.example.springshop.process.domain.answers.dto.CreateAnswerDto;
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
        List<Answers> answerList = answerService.selectUserAnswer(userId);
        return ApiResponseDto.of(answerList);
    }

}
