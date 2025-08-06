package com.Vethishan.quiz.app.Service;

import com.Vethishan.quiz.app.DAO.QuestionDAO;
import com.Vethishan.quiz.app.Entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;

    public List<Question> getAllQuestions() {
        return questionDAO.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDAO.findByCategory(category);
    }

    public String addQuestion(Question question) {
        questionDAO.save(question);
        return "Question added successfully";
    }
}
