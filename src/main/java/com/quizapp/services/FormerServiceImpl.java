package com.quizapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quizapp.models.Former;
import com.quizapp.repositories.FormerRepository;

@Service
public class FormerServiceImpl implements FormerService{

	
	private FormerRepository FormerRepository;

	public FormerServiceImpl(FormerRepository FormerRepository) {
		super();
		this.FormerRepository = FormerRepository;
	}

	@Override
	public List<Former> getAllFormers() {
		return FormerRepository.findAll();
	}

	@Override
	public Former saveFormer(Former Former) {
		return FormerRepository.save(Former);
	}

	@Override
	public Former getFormerById(Long id) {
		return FormerRepository.findById(id).get();
	}

	@Override
	public Former updateFormer(Former Former) {
		return FormerRepository.save(Former);
	}

	@Override
	public void deleteFormerById(Long Id) {
		FormerRepository.deleteById(Id);
	}

	@Override
	public Former findFormerByUsername(String username) {
		return FormerRepository.findByusername(username);
	}
	
}
