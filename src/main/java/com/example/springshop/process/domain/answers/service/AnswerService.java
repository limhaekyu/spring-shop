package com.example.springshop.process.domain.answers.service;

import com.example.springshop.process.domain.answers.domain.Answers;
import com.example.springshop.process.domain.answers.dto.CreateAnswerDto;
import com.example.springshop.process.domain.answers.dto.UpdateAnswerDto;
import com.example.springshop.process.domain.answers.repository.AnswerRepository;
import com.example.springshop.process.domain.questions.domain.Questions;
import com.example.springshop.process.domain.questions.service.QuestionService;
import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final UserService userService;
    private final QuestionService questionService;
    private final AnswerRepository answerRepository;

    public void createAnswer(Long userId, Long questionId, CreateAnswerDto createAnswerDto) {
        User user = userService.findUserById(userId);
        Questions question = questionService.findQuestionById(questionId);
        Answers answer = new Answers(createAnswerDto.getAnswerContents(), question, user);
        answerRepository.save(answer);
    }

    public Answers findAnswerById(Long answerId){
        Answers answer = answerRepository.findById(answerId).orElseThrow(
                () -> new IllegalArgumentException("없는 답변입니다.")
        );
        return answer;
    }

    public List<Answers> selectUserAnswer(Long userId) {
        User user = userService.findUserById(userId);
        List<Answers> answerList = answerRepository.findAllByUser(user);
        return answerList;
    }

    public List<Answers> selectAnswerToQuestion(Long questionId) {
        Questions question = questionService.findQuestionById(questionId);
        List<Answers> answerList = answerRepository.findAllByQuestion(question);
        return answerList;
    }

    public void updateAnswer(Long userId, Long answerId, UpdateAnswerDto updateAnswerDto) {
        User user = userService.findUserById(userId);
        Answers answer = findAnswerById(answerId);
        if (answer.getUser() == user){
            answer.updateAnswer(updateAnswerDto.getAnswerContents());
            answerRepository.save(answer);
        }
    }

    public void deleteAnswer(Long userId, Long answerId){
        User user = userService.findUserById(userId);
        Answers answer = findAnswerById(answerId);
        if(answer.getUser() == user){
            answerRepository.delete(answer);
        }
    }

}
