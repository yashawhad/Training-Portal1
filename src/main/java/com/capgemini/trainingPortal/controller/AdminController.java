package com.capgemini.trainingPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.trainingPortal.dto.AdminDto;
import com.capgemini.trainingPortal.dto.UserDto;
import com.capgemini.trainingPortal.entity.Admins;
import com.capgemini.trainingPortal.entity.Users;
import com.capgemini.trainingPortal.exception.InvalidCredentialsException;
import com.capgemini.trainingPortal.service.AdminService;
import com.capgemini.trainingPortal.service.UserService;
import com.capgemini.trainingPortal.service.UserTrainingAssignedService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserTrainingAssignedService userTrainingService;

	@PostMapping("/admin-register")
	public Admins registerUser(@RequestBody AdminDto adminDto) {
		return adminService.save(adminDto);
	}

	@PostMapping("/admin-login")
	public ResponseEntity<Admins> adminLogin(@RequestBody AdminDto adminDto) {
		try {
			Admins loggedInAdmin = adminService.adminLogin(adminDto);

			return ResponseEntity.ok().body(loggedInAdmin);
		} catch (InvalidCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}

	// Gourav resgiter a new user by admin
	@PostMapping("/register")
	public Users registerUser(@RequestBody UserDto userDto) {
		return userService.save(userDto);
	}
//	    @PostMapping("/{userId}/assign-trainings")
//	    public ResponseEntity<String> assignTrainingsToUser(@PathVariable Long userId, @RequestBody List<Long> trainingIds) {
//	        try {
//	            adminService.assignTrainingsToUser(userId, trainingIds);
//	            return ResponseEntity.ok("Trainings assigned successfully to the user.");
//	        } catch (Exception e) {
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to assign trainings to the user.");
//	        }
//	    }

//sweta As a training administrator,
//	 I want to assign specific trainings to individual users,
	@PostMapping("/assign-training/{userId}/{trainingId}")
	public ResponseEntity<?> assignTrainingToUser(@PathVariable Long userId, @PathVariable Long trainingId) {
		userTrainingService.assignTraining(userId, trainingId);
		return ResponseEntity.ok("training added successfully");
	}

	@PostMapping("/logout")
	public ResponseEntity<String> adminLogout(HttpServletRequest request) {
		// Log out the current user by invalidating the session
		request.getSession().invalidate();
		return ResponseEntity.ok("Logged out successfully.");
	}

}
