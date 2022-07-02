package com.example.springshop.process.domain.questions.domain;

import com.example.springshop.process.domain.answers.domain.Answers;
import com.example.springshop.process.domain.user.domain.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "questions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @Column(name = "question_title")
    private String questionTitle;

    @Column(name = "question_contents")
    private String questionContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @JsonBackReference
    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Answers> answer = new ArrayList<>();


    public Questions(String questionTitle, String questionContents, User user) {
        this.questionTitle = questionTitle;
        this.questionContents = questionContents;
        this.user = user;
    }

    public void updateQuestion(String questionTitle, String questionContents) {
        this.questionTitle = questionTitle;
        this.questionContents = questionContents;
    }
}
