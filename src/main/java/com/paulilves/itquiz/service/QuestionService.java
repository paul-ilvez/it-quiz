package com.paulilves.itquiz.service;

import com.paulilves.itquiz.config.Constants;
import com.paulilves.itquiz.dto.TestInput;
import com.paulilves.itquiz.entity.Answer;
import com.paulilves.itquiz.entity.Question;
import com.paulilves.itquiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question findQuestion(Long id) {
        return questionRepository.getOne(id);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> getQuestionsShuffled() {
        List<Question> interim = new ArrayList<>(questionRepository.findAll());
        long seed = System.nanoTime();
        Collections.shuffle(interim, new Random(seed));
        List<Question> questions = new ArrayList<>(interim.subList(0, (Constants.QUESTIONS_TOTAL - 1)));
        return questions;
    }

    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }


    public String addUserAnswer(TestInput testInput, Answer answer) {
        testInput.getCurrentQuestion().setUserAnswer(answer);
        testInput.increaseCounter();

        if (testInput.getCurrentNumber() == (Constants.QUESTIONS_TOTAL - 1)) {
            return "finish";
        } else {
            return "question";
        }

    }

    public Answer processUserInput(String answerText) {
        if (answerText != null) {
            return new Answer(answerText, false);
        } else {
            return new Answer("No answer given", false);
        }
    }

    public void endTest(TestInput testInput) {
        for (Question question : testInput.getQuestions()) {
            if (question.getUserAnswer() == null) {
                question.setUserAnswer(new Answer("No answer given", false));
            }
        }
    }

    public String jumpTo(TestInput testInput, int jumpToPage) {
        --jumpToPage;

        if (testInput.getCurrentNumber() < jumpToPage) {
            for (Question question : testInput.getQuestions()) {
                if (question.getUserAnswer() == null) {
                    question.setUserAnswer(new Answer("No answer given", false));
                }
            }
        }
        if(jumpToPage==998) return "finish";

        testInput.setCurrentNumber(jumpToPage);
        return "question";
    }
}
