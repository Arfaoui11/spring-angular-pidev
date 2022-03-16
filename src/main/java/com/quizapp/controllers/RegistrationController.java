package com.quizapp.controllers;

import com.quizapp.models.Former;
import com.quizapp.services.FormerService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.quizapp.dto.UserDTO;
import com.quizapp.models.Admin;
import com.quizapp.models.Learner;

import com.quizapp.services.AdminService;
import com.quizapp.services.LearnerService;


@Controller
public class RegistrationController {

	@Autowired
	private LearnerService LearnerService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private FormerService formerService;

	@PostMapping("/register")
	public String registerNewUser(@ModelAttribute UserDTO userDto) {
		if (userDto.getRole().equalsIgnoreCase("Learner")) {
			Learner Learner = new Learner();
			Learner.setFirstName(userDto.getFirstName());
			Learner.setLastName(userDto.getLastName());
			Learner.setUsername(userDto.getUsername());
			Learner.setPassword(userDto.getPassword());
			LearnerService.saveLearner(Learner);
		} else if (userDto.getRole().equalsIgnoreCase("admin")) {
			Admin admin = new Admin();
			admin.setFirstName(userDto.getFirstName());
			admin.setLastName(userDto.getLastName());
			admin.setUsername(userDto.getUsername());
			admin.setPassword(userDto.getPassword());
			adminService.saveAdmin(admin);
		} else if (userDto.getRole().equalsIgnoreCase("former")) {
			Former former = new Former();
			former.setFirstName(userDto.getFirstName());
			former.setLastName(userDto.getLastName());
			former.setUsername(userDto.getUsername());
			former.setPassword(userDto.getPassword());
			formerService.saveFormer(former);
		}
		return "redirect:/admin/dashboard";
	}

}
