package com.javachinna.service;

import com.javachinna.model.Quiz;
import com.javachinna.repo.QuizRepository;
import org.springframework.stereotype.Service;

@Service
public class QuizServiceImpl implements QuizService{
	
	
	private QuizRepository quizRepository;
	
	
	public QuizServiceImpl(QuizRepository quizRepository) {
		super();
		this.quizRepository = quizRepository;
	}


	public Quiz saveQuiz(Quiz quiz) {
		return quizRepository.save(quiz);
	}
	
}
