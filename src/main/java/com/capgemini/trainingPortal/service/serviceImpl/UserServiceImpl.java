package com.capgemini.trainingPortal.service.serviceImpl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
//irg.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;mport o
import org.springframework.stereotype.Service;

import com.capgemini.trainingPortal.dto.TrainingDto;
import com.capgemini.trainingPortal.dto.UserDto;
import com.capgemini.trainingPortal.dto.UserLoginDto;
import com.capgemini.trainingPortal.entity.Trainings;
import com.capgemini.trainingPortal.entity.Users;
import com.capgemini.trainingPortal.exception.EmailAlreadyRegisteredException;
import com.capgemini.trainingPortal.exception.InvalidCredentialsException;
import com.capgemini.trainingPortal.exception.UserNotFoundException;
import com.capgemini.trainingPortal.repository.TrainingRepository;
import com.capgemini.trainingPortal.repository.UserRepository;
import com.capgemini.trainingPortal.service.UserService;

import jakarta.transaction.Transactional;

@Service
public  class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TrainingRepository trainingRepository;
	
	 

	@Override
	public Users save(UserDto userDto) {
		Users existingUser = userRepository.findByEmail(userDto.getEmail());
		if (existingUser != null) {
			throw new EmailAlreadyRegisteredException("Email is already registered.");
		}
		
		Users users = new Users(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getUsername(),
				userDto.getEmail(), userDto.getPassword(), userDto.getRole(), userDto.getGrade());
//		Users users=Users.builder().email(null).build();
		return userRepository.save(users);
	}

	@Override
	public Users login(UserLoginDto userDto) {
		Users existingUser = userRepository.findByEmail(userDto.getEmail());
		if (existingUser == null || !existingUser.getPassword().equals(userDto.getPassword())) {
			throw new InvalidCredentialsException("Invalid email or password.");
		}
		return existingUser;
	}

	@Override
	public Users resetPassword(UserDto userDto) {
		Users existingUser = userRepository.findByEmail(userDto.getEmail());
		if (existingUser == null) {
			throw new UserNotFoundException("User with email " + userDto.getEmail() + " not found.");
		}
		// Update the user's password with the new password
		existingUser.setPassword(userDto.getPassword());
		userRepository.save(existingUser);
		return existingUser;
	}

//	@Override
////	public UserDto getCurrentUser() {
////		// Retrieve currently authenticated user from Spring Security context
////		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////
////		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
////			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
////			// Assuming your UserDetails implementation contains the necessary user details
////			return UserDto.builder().email(userDetails.getUsername()).build();
////		} else {
////			// User not authenticated or UserDetails not found in SecurityContext
////			// Handle accordingly, e.g., return null or throw an exception
////			return null;
////		}
////	}

	

	@Override
	public void updateTrainingProgress(Long userId, List<Long> completedTrainingIds) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public List<Long> getCompletedTrainings(Long userId) {
		Users user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

		// Return the user's completed training IDs
		return null;
	}

	@Override
	public void generateGraphicalReport(Long userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDto getCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}
	
	 private TrainingDto convertToDto(Trainings trainings) {
	        // Convert Trainings entity to TrainingDto
	        TrainingDto trainingDto = new TrainingDto();
	        trainingDto.setId(trainings.getId());
	        trainingDto.setName(trainings.getName());
	        trainingDto.setDescription(trainings.getDescription());
	        trainingDto.setProjects(trainings.getProjects());

	        return trainingDto;
	    }

	 @Override
	 public String getLoggedInUserGrade() {
	     // Assuming you have some way to uniquely identify the logged-in user, such as a username or user ID
	     String loggedInUsername = "dawhad"; // Example: Replace "exampleUser" with the actual identifier of the logged-in user
	     
	     // Retrieve user by username from the repository
	     Users user = userRepository.findByEmail(loggedInUsername);
	     
	     // Check if user exists
	     if (user != null) {
	         // Return the user's grade
	         return user.getGrade(); // Assuming User entity has a 'grade' attribute
	     } else {
	         // Handle case where user is not found
	         return null;
	     }
	 }

	 @Override
	 public List<TrainingDto> getAvailableTrainingsForLoggedInUser() {
	     // Get logged-in user's grade
	     String userGrade = getLoggedInUserGrade(); // Corrected method call
	     
	     // Get available trainings for the logged-in user's grade
	     List<Trainings> trainingsList = trainingRepository.findAllByGradesContaining(userGrade);
	     return trainingsList.stream()
	                         .map(this::convertToDto1)
	                         .collect(Collectors.toList());
	 }

	 private TrainingDto convertToDto1(Trainings trainings) { // Corrected method name
	     // Convert Trainings entity to TrainingDto
	     TrainingDto trainingDto = new TrainingDto();
	     trainingDto.setId(trainings.getId());
	     trainingDto.setName(trainings.getName());
	     trainingDto.setDescription(trainings.getDescription());
	     trainingDto.setProjects(trainings.getProjects());

	     return trainingDto;
	 }


	

	
	
	   

	

	

	

}
