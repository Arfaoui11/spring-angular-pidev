package com.quizapp.services;


import java.util.List;

import com.quizapp.models.Admin;
import org.springframework.stereotype.Service;


public interface AdminService {

	List<Admin> getAllAdmins();
	Admin saveAdmin(Admin admin);
	Admin getAdminById(Long Id);
	void deleteAdminById(Long Id);
	
}
