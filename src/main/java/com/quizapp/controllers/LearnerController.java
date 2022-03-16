package com.quizapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.quizapp.models.Learner;
import com.quizapp.services.LearnerService;

@Controller
public class LearnerController {

	
	private LearnerService LearnerService;
	

	public LearnerController(LearnerService LearnerService) {
		super();
		this.LearnerService = LearnerService;
	}

	// Only Learners can register
	@GetMapping("/register/new")
	public String showLearnerRegistrationForm(Model model) {
		Learner Learner = new Learner();
		model.addAttribute("Learner", Learner);
		return "register";
	}

	
	@PostMapping("/Learners")
	public String createLearner(@ModelAttribute("Learner") Learner Learner) {
		Learner.setRole("Learner");
		LearnerService.saveLearner(Learner);
		return "redirect:/login/Learner";
	}


	@GetMapping("/Learner/dashboard")
	public String showLearnerDashboard(Model model) {
		model.addAttribute("Learner", ContextController.getLearner());
		return "Learner-dashboard";
	}
	
	@GetMapping("/Learner/edit/{id}")
	public String showUpdateLearnerPage(@PathVariable Long id, Model model) {
		Learner Learner = LearnerService.getLearnerById(id);
		model.addAttribute("Learner", Learner);
		return "admin-Learner-edit";
	}
	
	
	//ADMIN ACTIONS ON LearnerS ENITITY*****
	@GetMapping("/Learner/delete/{id}")
	public String deleteLearner(@PathVariable Long id, Model model) {
		LearnerService.deleteLearnerById(id);
		return "redirect:/admin/dashboard";
	}
	
	//Update existing Learner
	@PostMapping("/Learners/{id}")
	public String updateLearner(@PathVariable Long id,
			@ModelAttribute("Learner") Learner Learner,
			Model model) {
		Learner existingLearner = LearnerService.getLearnerById(id);
		existingLearner.setId(id);
		existingLearner.setFirstName(Learner.getFirstName());
		existingLearner.setLastName(Learner.getLastName());
		existingLearner.setUsername(Learner.getUsername());
		existingLearner.setPassword(Learner.getPassword());
		LearnerService.updateLearner(existingLearner);
		return "redirect:/admin/dashboard";		
	}
	
	

}
