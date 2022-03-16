package com.quizapp.services;

import java.util.List;

import com.quizapp.models.Learner;

public interface LearnerService {
	List<Learner> getAllLearners();
	Learner saveLearner(Learner Learner);
	Learner getLearnerById(Long id);
	Learner updateLearner(Learner Learner);
	void deleteLearnerById(Long Id);
	
}
