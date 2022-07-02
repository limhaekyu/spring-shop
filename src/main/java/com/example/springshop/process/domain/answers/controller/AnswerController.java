package com.example.springshop.process.domain.answers.controller;

import com.example.springshop.process.domain.answers.dto.CreateAnswerDto;
import com.example.springshop.process.domain.answers.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    //CRUD

    @PostMapping("api/shop/{userId}/answer")
    public void createAnswer(@PathVariable Long userId, @RequestParam Long questionId, @RequestBody CreateAnswerDto createAnswerDto){
        answerService.createAnswer(userId, questionId, createAnswerDto);
    }
}
