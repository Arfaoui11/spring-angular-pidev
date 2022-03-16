package com.quizapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizapp.models.Admin;
import com.quizapp.repositories.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	
	private AdminRepository adminRepository;
	
	
	public AdminServiceImpl(AdminRepository adminRepository) {
		super();
		this.adminRepository = adminRepository;
	}


	@Override
	public List<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}


	@Override
	public Admin saveAdmin(Admin admin) {
		return adminRepository.save(admin);
	}


	@Override
	public Admin getAdminById(Long Id) {
		return adminRepository.getById(Id);
	}


	@Override
	public void deleteAdminById(Long Id) {
		adminRepository.deleteById(Id);
	}
	
	

}
