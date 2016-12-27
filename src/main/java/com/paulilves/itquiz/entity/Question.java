package com.paulilves.itquiz.entity;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.*;

@Entity(name = "question")
@Table(name = "question")
@Proxy(lazy = false)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String content;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Answer> answers = new ArrayList<>();

    @Transient
    private Answer userAnswer;

    public Question() {
    }

    public Question(String content, String correctAnswerText, String... wrongAnswersTexts) {
        this.content = content;
        List<Answer> interim = new ArrayList<>();
        interim.add(new Answer(correctAnswerText, true));

        for (String wrongAnswer : wrongAnswersTexts) {
            interim.add(new Answer(wrongAnswer, false));
        }
        long seed = System.nanoTime();
        Collections.shuffle(interim, new Random(seed));
        this.answers.addAll(interim);

    }

    public Question(String content, List<Answer> answers) {
        this.content = content;
        this.answers = answers;
    }

    public Answer getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(Answer userAnswer) {
        this.userAnswer = userAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public void addAnswers(Collection<Answer> answers) {
        this.answers.addAll(answers);
    }

    public void addAnswers(Answer... answers) {
        for (Answer answer : answers) {
            this.answers.add(answer);
        }
    }

    public boolean isCorrectlyAnswered(){
        if (userAnswer.getContent().equalsIgnoreCase(this.getCorrectAnswer().getContent())) {
            return true;
        } else return false;
    }

    public Answer getCorrectAnswer() {
        for (Answer answer : this.answers) {
            if (answer.isCorrect()) return answer;
        }

        return null;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", answers=" + answers +
                '}';
    }
}
