package com.example.springshop.process.domain.questions.service;

import com.example.springshop.process.domain.questions.domain.Questions;
import com.example.springshop.process.domain.questions.dto.AddQuestionDto;
import com.example.springshop.process.domain.questions.dto.UpdateQuestionDto;
import com.example.springshop.process.domain.questions.repository.QuestionRepository;
import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserService userService;

    public void addQuestion(Long userId, AddQuestionDto addQuestionDto) {
        User user = userService.findUserById(userId);
        Questions questions = new Questions(addQuestionDto.getQuestionTitle(), addQuestionDto.getQuestionContents(), user);
        questionRepository.save(questions);
    }

    public List<Questions> selectAllQuestion() {
        List<Questions> allQuestionList = questionRepository.findAll();
        return allQuestionList;
    }

    public List<Questions> selectUserQuestion(Long userId) {
        User user = userService.findUserById(userId);
        List<Questions> userQuestionList = questionRepository.findAllByUser(user);
        return userQuestionList;
    }


    public void updateQuestion(Long userId, Long questionId, UpdateQuestionDto updateQuestionDto) {
        User user = userService.findUserById(userId);
        Questions question = findQuestionById(questionId);
        if (question.getUser() == user){
            question.updateQuestion(updateQuestionDto.getQuestionTitle(),updateQuestionDto.getQuestionContents());

            questionRepository.save(question);
        }

    }

    public Questions findQuestionById(Long questionId){
        Questions question = questionRepository.findById(questionId).orElseThrow(
                () -> new IllegalArgumentException("없는 질문입니다."));
        return question;
    }

    public void deleteQuestion(Long userId, Long questionId) {
        User user = userService.findUserById(userId);
        Questions question = findQuestionById(questionId);
        if(question.getUser() == user){
            questionRepository.delete(question);
        }

    }
}
