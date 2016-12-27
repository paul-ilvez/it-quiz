package com.paulilves.itquiz.controller;

import com.paulilves.itquiz.dto.TestInput;
import com.paulilves.itquiz.entity.Answer;
import com.paulilves.itquiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes({"testInput"})
@RequestMapping(path = "/questions")
public class QuizController {

    //    @Autowired
//    UserService userService;
//
    @Autowired
    QuestionService questionService;

    //leads to: exams description page
    //initializes: testInput object (used to hold data about user's inputs)
    @RequestMapping(value = {"/start", ""})
    public ModelAndView startExam() {
        ModelAndView view = new ModelAndView("index");
        return view;
    }

    //leads to: first question
    @RequestMapping(value = {"/first", "/retry"})
    public ModelAndView firstQuestion(HttpSession httpSession) {

        TestInput testInput = new TestInput();
        testInput.setQuestions(questionService.getQuestionsShuffled());
        httpSession.setAttribute("testInput", testInput);

        ModelAndView view = new ModelAndView("question");
        view.addObject("testInput", testInput);
        return view;
    }


    //leads to: every next question after the first one
    @RequestMapping(value = "/next")
    public String nextQuestion(@ModelAttribute("testInput") TestInput testInput, @RequestParam(value = "userAnswer", required = false) String answerText) {
        Answer answer = questionService.processUserInput(answerText);
        return questionService.addUserAnswer(testInput, answer);
    }

    @RequestMapping("/finish")
    public String finish(@ModelAttribute("testInput") TestInput testInput) {
        questionService.endTest(testInput);
        return "finish";
    }

    @RequestMapping("/goto")
    public String jumpTo(@ModelAttribute("testInput") TestInput testInput, @RequestParam("jumpToPage") int jumpToPage) {
        return questionService.jumpTo(testInput, jumpToPage);
    }

}
