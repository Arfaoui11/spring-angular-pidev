package com.quizapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quizapp.models.Learner;
import com.quizapp.repositories.LearnerRepository;

@Service
public class LearnerServiceImpl implements LearnerService {

	private LearnerRepository LearnerRepository;

	public LearnerServiceImpl(LearnerRepository LearnerRepository) {
		super();
		this.LearnerRepository = LearnerRepository;
	}

	@Override
	public List<Learner> getAllLearners() {
		return LearnerRepository.findAll();
	}

	@Override
	public Learner saveLearner(Learner Learner) {
		return LearnerRepository.save(Learner);
	}

	
	public Learner findLearnerByUsernameAndPassword(String username){
	        Learner Learner = LearnerRepository.findByusername(username);
	        return Learner; 
	 }

	@Override
	public Learner getLearnerById(Long id) {
		return LearnerRepository.findById(id).get();
	}

	@Override
	public Learner updateLearner(Learner Learner) {
		return LearnerRepository.save(Learner);
	}

	@Override
	public void deleteLearnerById(Long Id){
		 LearnerRepository.deleteById(Id);
	}
}
