package com.example.springshop.process.domain.questions.service;

import com.example.springshop.process.domain.questions.domain.Questions;
import com.example.springshop.process.domain.questions.dto.AddQuestionDto;
import com.example.springshop.process.domain.questions.repository.QuestionRepository;
import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserService userService;

    public void addQuestion(Long id, AddQuestionDto addQuestionDto) {
        User user = userService.findUserById(id);
        Questions questions = new Questions(addQuestionDto.getQuestionTitle(), addQuestionDto.getQuestionContents(), user);
        questionRepository.save(questions);
    }

    public List<Questions> selectAllQuestion() {
        List<Questions> allQuestionList = questionRepository.findAll();
        return allQuestionList;
    }

    public List<Questions> selectUserQuestion(Long id) {
        User user = userService.findUserById(id);
        List<Questions> userQuestionList = questionRepository.findAllByUser(user);
        return userQuestionList;
    }
}
