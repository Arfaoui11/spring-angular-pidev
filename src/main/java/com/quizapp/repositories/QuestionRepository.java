package com.quizapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.models.Question;
import org.springframework.stereotype.Repository;

@Repository

public interface QuestionRepository extends JpaRepository<Question, Long> {

	List<Question> findBysubject(String subject);
	Question findByquestionId(Long id);
	void deleteByquestionId(Long id);
}
