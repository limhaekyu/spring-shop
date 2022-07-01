package com.example.springshop.process.domain.questions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddQuestionDto {

    private String questionTitle;

    private String questionContents;

}
