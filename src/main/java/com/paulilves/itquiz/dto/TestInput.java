package com.paulilves.itquiz.dto;

import com.paulilves.itquiz.entity.Answer;
import com.paulilves.itquiz.entity.Question;

import java.util.ArrayList;
import java.util.List;

//A simple DTO for carrying over data about the user's current questions and answers in the test
//Used for containing answers made and current position in the test
public class TestInput {


    private int currentNumber;
    private int correctAnswers;
    private List<Question> questions = new ArrayList<>();

    public TestInput() {
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
    }

    public Question getCurrentQuestion() {
        return questions.get(currentNumber);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void increaseCounter() {
        currentNumber++;
    }

    public int getCorrectAnswers() {
        calculateCorrectAnswers();
        return correctAnswers;
    }

    private void calculateCorrectAnswers() {
        for (Question question : questions) {
            if (question.isCorrectlyAnswered()) {
                ++correctAnswers;
            }
        }
    }

    public int getTotalQuestionNumber() {
        return questions.size();
    }

}
