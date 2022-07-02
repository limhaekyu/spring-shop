package com.example.springshop.process.domain.questions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateQuestionDto {

    private String questionTitle;

    private String questionContents;
}
