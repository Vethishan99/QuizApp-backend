package com.Vethishan.quiz.app.DAO;

import com.Vethishan.quiz.app.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDAO extends JpaRepository<Quiz, Integer> {

}
