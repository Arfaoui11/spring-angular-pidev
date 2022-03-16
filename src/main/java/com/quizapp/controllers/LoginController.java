package com.quizapp.controllers;

import com.quizapp.models.Former;
import com.quizapp.services.FormerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.quizapp.dto.ForgotPasswordDTO;
import com.quizapp.models.Admin;
import com.quizapp.models.Learner;

import com.quizapp.repositories.AdminRepository;
import com.quizapp.repositories.LearnerRepository;


@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LearnerRepository LearnerRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private FormerService formerService;

 	@GetMapping("/admin")
	public String showAdminLoginForm(Model model) {
 		Admin admin = new Admin();
 		model.addAttribute("admin", admin);
		return "admin-login";
	}
	
 	@GetMapping("/Former")
	public String showformerLoginForm(Model model) {
 		Former former = new Former();
 		model.addAttribute("Former", former);
		return "former-login";
	}
 	
 	@GetMapping("/Learner")
	public String showLearnerLoginForm(Model model) {
 		Learner Learner = new Learner();
 		model.addAttribute("Learner", Learner);
		return "Learner-login";
	}
 	
 	@PostMapping("/Learner")
 	public String loginLearner(@ModelAttribute("Learner") Learner Learner) {
 		Learner savedLearner = LearnerRepository.findByusername(Learner.getUsername());
 		if(savedLearner != null) {
 			 ContextController.setLearner(savedLearner);
	 		 if(savedLearner.getPassword().equalsIgnoreCase(Learner.getPassword())) {
	 			return "redirect:/Learner/dashboard";
	 		 }
 		 }
 		System.out.println("Learner Login failed");
		return "redirect:/login/Learner?error";
 	}
 	
 	@PostMapping("/admin")
 	public String loginAdmin(@ModelAttribute("admin") Admin admin) {
 		Admin savedAdmin = adminRepository.findByusername(admin.getUsername());
 		if(savedAdmin != null) {
 			ContextController.setAdmin(savedAdmin);
 			if(savedAdmin.getPassword().equalsIgnoreCase(admin.getPassword())) {
 				return "redirect:/admin/dashboard";
 			}
 		}
 		System.out.println("Admin Login failed");
		return "redirect:/login/admin?error";
 	}
 	
 	@PostMapping("/Former")
 	public String loginformer(@ModelAttribute("Former") Former former) {
		Former savedformer = formerService.findFormerByUsername(former.getUsername());
		if(savedformer != null) {
			ContextController.setformer(savedformer);
			if(savedformer.getPassword().equalsIgnoreCase(former.getPassword())) {
 				return "redirect:/Former/dashboard";
 			}
		}
		System.out.println("former Login failed");
		return "redirect:/login/Former?error";
 	}
 	
 	
 	@GetMapping("/reset")
 	public String showforgotPasswordForm(Model model) {
 		ForgotPasswordDTO forgotPasswordDto = new ForgotPasswordDTO();
 		model.addAttribute("forgotPasswordDto", forgotPasswordDto);
		return "forgot-password";
 	}
 	
 	
 	@PostMapping("/reset")
 	public String resetLearnerPassword(@ModelAttribute ForgotPasswordDTO forgotPasswordDTO) {
 		if(!forgotPasswordDTO.getNewPassword().equals(forgotPasswordDTO.getNewConfirmPassword())) {
 			return "redirect:/reset?mismatch";
 		}
 		Learner Learner = LearnerRepository.findByusername(forgotPasswordDTO.getUsername());
 		if(Learner == null) return "redirect:/reset?errorusername";
 		Learner.setPassword(forgotPasswordDTO.getNewPassword());
 		LearnerRepository.save(Learner);
 		return "redirect:/login/Learner";
 	}

}
