package com.quizapp.services;

import java.util.List;

import com.quizapp.models.Former;

public interface FormerService {
	List<Former> getAllFormers();
	Former findFormerByUsername(String username);
	Former saveFormer(Former Former);
	Former getFormerById(Long id);
	Former updateFormer(Former Former);
	void deleteFormerById(Long Id);
}
