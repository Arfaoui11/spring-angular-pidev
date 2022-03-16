package com.quizapp.controllers;

import java.util.List;

import com.quizapp.services.FormerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.quizapp.dto.UserDTO;
import com.quizapp.models.Admin;
import com.quizapp.models.Learner;
import com.quizapp.models.Former;

import com.quizapp.repositories.AdminRepository;
import com.quizapp.repositories.LearnerRepository;
import com.quizapp.services.AdminService;
import com.quizapp.services.LearnerService;

@Controller
public class AdminController {

	@Autowired
	private LearnerService LearnerService;

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private FormerService formerService;

	@GetMapping("/admin/dashboard")
	public String showAdminDashboard(Model model) {
		List<Learner> Learners = LearnerService.getAllLearners();
		List<Admin> admins = adminService.getAllAdmins();
		List<Former> formers = formerService.getAllFormers();
		model.addAttribute("admin", ContextController.getAdmin());
		model.addAttribute("Learners", Learners);
		model.addAttribute("admins", admins);
		model.addAttribute("formers", formers);
		return "admin-dashboard";
	}

	@GetMapping("/admin/users")
	public String showAddUsersPage(Model model) {
		UserDTO userDTO = new UserDTO();
		model.addAttribute("admin", ContextController.getAdmin());
		model.addAttribute("userDto", userDTO);
		return "admin-add-user";
	}
	
	
	@GetMapping("/admin/edit/{id}")
	public String showAdminEditPage(@PathVariable Long id, Model model) {
		Admin admin = adminService.getAdminById(id);
		model.addAttribute("admin", admin);
		return "admin-admin-edit";
	}
	

	@PostMapping("/admin/edit/{id}")
	public String updateAdminDetails(@PathVariable Long id, @ModelAttribute("admin") Admin admin,
			Model model) {
		Admin existingAdmin = adminService.getAdminById(id);
		existingAdmin.setFirstName(admin.getFirstName());
		existingAdmin.setLastName(admin.getLastName());
		existingAdmin.setUsername(admin.getUsername());
		existingAdmin.setPassword(admin.getPassword());
		adminService.saveAdmin(existingAdmin);
		return "redirect:/admin/dashboard";
	}
	
	@GetMapping("/admin/delete/{id}")
	public String deleteAdminDetails(@PathVariable Long id,
			Model model) {
		List<Admin> admins = adminService.getAllAdmins();
		if(admins.size() == 1) {
			return "redirect:/admin/dashboard?adminerror";
		}
		adminService.deleteAdminById(id);
		return "redirect:/admin/dashboard";
	}
	
	

}
