package com.paulilves.itquiz;


import com.paulilves.itquiz.entity.Question;
import com.paulilves.itquiz.service.QuestionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }

    @Bean
    public CommandLineRunner demo(QuestionService questionService) {
        return (args) -> {
            // save a couple of questions
            questionService.saveQuestion(new Question("Which company bought Sun in 2009?", "Oracle", "Microsoft", "Apple", "Gazprom"));
            questionService.saveQuestion(new Question("What the codename for processors able to perform 64-bit operations?", "x64", "x86", "x32"));
            questionService.saveQuestion(new Question("What was the original name of Java?", "Oak", "Capuccino", "Latte"));
            questionService.saveQuestion(new Question("What is not a Java primitive type?", "void", "int", "boolean", "char"));
            questionService.saveQuestion(new Question("Which feature has been added only in Java 1.8?", "lambda functions", "assert", "enum", "auto-boxing"));
            questionService.saveQuestion(new Question("Who is the chief architect behind Java?", "Jame Gosling", "Guido van Rossum", "Dennis Ritchie", "Rasmus Lerdorf"));
            questionService.saveQuestion(new Question("Which keyword is NOT related to multi-threading?", "transient", "volatile", "synchronize"));
            questionService.saveQuestion(new Question("Which package is implicitly imported to any Java class?", "java.lang", "java.util", "com.sun", "java.system"));
            questionService.saveQuestion(new Question("Which of these memory spaces is not shared by all threads?", "Stack", "PermGen", "Heap", "MetaSpace"));
            questionService.saveQuestion(new Question("What is the status of the notorious \"goto\" keyword in java?", "Reserved, but not used", "Used", "Used, but discouraged", "Unreserved and not used"));
            questionService.saveQuestion(new Question("Which class is the superclass for every class?", "Object", "Class", "Main", "Base"));
            questionService.saveQuestion(new Question("What cannot be marked as private in Java?", "constructor", "field", "parameter", "method"));
            questionService.saveQuestion(new Question("What collection interface does not extends the Iterable interface?", "Map", "List", "Queue", "Set"));
        };

    }
}
