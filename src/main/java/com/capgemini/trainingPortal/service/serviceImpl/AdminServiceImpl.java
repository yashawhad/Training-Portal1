package com.capgemini.trainingPortal.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.trainingPortal.dto.AdminDto;
import com.capgemini.trainingPortal.entity.Admins;
import com.capgemini.trainingPortal.entity.Users;
import com.capgemini.trainingPortal.exception.EmailAlreadyRegisteredException;
import com.capgemini.trainingPortal.exception.InvalidCredentialsException;
import com.capgemini.trainingPortal.exception.UserNotFoundException;
import com.capgemini.trainingPortal.repository.AdminRepository;
import com.capgemini.trainingPortal.repository.UserRepository;
import com.capgemini.trainingPortal.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public Admins adminLogin(AdminDto adminDto) {
		Admins existingAdmin = adminRepository.findByEmail(adminDto.getEmail());
		if (existingAdmin == null || !existingAdmin.getPassword().equals(adminDto.getPassword())) {
			throw new InvalidCredentialsException("Invalid email or password for admin.");
		}
		return existingAdmin;

	}

	@Override
	public Admins save(AdminDto adminDto) {
		Admins existingAdmin = adminRepository.findByEmail(adminDto.getEmail());
		if (existingAdmin != null) {
			throw new EmailAlreadyRegisteredException("Email is already registered.");
		}
		Admins admins = new Admins(adminDto.getId(), adminDto.getFirstName(), adminDto.getLastName(),
				adminDto.getUserName(), adminDto.getEmail(), adminDto.getPassword(), adminDto.getRole(),
				adminDto.getAssignedTrainings());
		return adminRepository.save(admins);

	}

	@Override
	public void assignTrainingsToUser(Long userId, List<Long> trainingIds) {
		Users user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

		// Assign the specified trainings to the user
		user.getAssignedTrainings().addAll(trainingIds);

		// Save the updated user
		userRepository.save(user);
	}

}
