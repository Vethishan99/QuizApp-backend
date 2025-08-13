package com.Vethishan.quiz.app.Service;

import com.Vethishan.quiz.app.DAO.QuestionDAO;
import com.Vethishan.quiz.app.DAO.QuizDAO;
import com.Vethishan.quiz.app.Entity.Question;
import com.Vethishan.quiz.app.Entity.QuestionWrapper;
import com.Vethishan.quiz.app.Entity.Quiz;
import com.Vethishan.quiz.app.Entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDAO quizDAO;
    @Autowired
    QuestionDAO questionDAO;

    public ResponseEntity<String>createQuiz(String category, int numQ, String title){
        List<Question> questions=questionDAO.findRandomQuestionsByCategory(category,numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDAO.save(quiz);

        return new ResponseEntity<>("Quiz created with ID: " + quiz.getId(), HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizDAO.findById(id);
        List<Question> questionsFromDB=quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser=new ArrayList<>();
        for(Question q:questionsFromDB){
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getCategory(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResults(int id, List<Response> responses) {
        Quiz quiz = quizDAO.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        int right=0;
        int i=0;
        for(Response r:responses){
            if(r.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;

            i++;
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
