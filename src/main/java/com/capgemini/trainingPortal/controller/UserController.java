package com.capgemini.trainingPortal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.trainingPortal.dto.TrainingDto;
import com.capgemini.trainingPortal.dto.UserDto;
import com.capgemini.trainingPortal.dto.UserLoginDto;
import com.capgemini.trainingPortal.entity.Trainings;
import com.capgemini.trainingPortal.entity.UserTrainingAssigned;
import com.capgemini.trainingPortal.entity.UserTrainingCompleted;
import com.capgemini.trainingPortal.entity.Users;
import com.capgemini.trainingPortal.exception.InvalidCredentialsException;
import com.capgemini.trainingPortal.exception.UserNotFoundException;
import com.capgemini.trainingPortal.repository.TrainingRepository;
import com.capgemini.trainingPortal.repository.UserRepository;
import com.capgemini.trainingPortal.repository.UserTrainingAssignedRepository;
import com.capgemini.trainingPortal.service.UserService;
import com.capgemini.trainingPortal.service.UserTrainingCompletedService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
//@RequestMapping("/api/users")
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	  @Autowired
	    private UserService userService;
	  @Autowired
	    private UserRepository userRepository;
	  @Autowired
	    private TrainingRepository trainingRepository;
	  
	  @Autowired
	  private UserTrainingAssignedRepository userTrainingAssignedRepository;

	  @Autowired
	    private UserTrainingCompletedService userTrainingCompletedService;

	    @PostMapping("/register")
	    public Users registerUser(@Valid @RequestBody UserDto userDto) {
	        return userService.save(userDto);
	    }
	    
	    @PostMapping("/login")
	    public ResponseEntity<Users> login(@RequestBody UserLoginDto userLoginDto) {
	        try {
	            Users loggedInUser = userService.login(userLoginDto);
	            // Optionally, you can return additional user information along with a success message
	            return ResponseEntity.ok(loggedInUser);
	        } catch (InvalidCredentialsException e) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	        }
	    }
	    
	    @PutMapping("/reset-password")
	    public ResponseEntity<String> resetPassword(@RequestBody UserDto userDto) {
	        try {
	            userService.resetPassword(userDto);
	            return (ResponseEntity<String>) ResponseEntity.ok("Password reset successfully.");
	        } catch (UserNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        }
	    }
	    
	    
	
	    
	    @PutMapping("/{userId}/update-training-progress")
	    public ResponseEntity<String> updateTrainingProgress(@PathVariable Long userId, @RequestBody List<Long> completedTrainingIds) {
	        try {
	            userService.updateTrainingProgress(userId, completedTrainingIds);
	            return ResponseEntity.ok("Training progress updated successfully.");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update training progress.");
	        }
	    }
	    
	    @GetMapping("/{userId}/completed-trainings")
	    public ResponseEntity<List<Long>> getCompletedTrainings(@PathVariable Long userId) {
	        try {
	            List<Long> completedTrainings = userService.getCompletedTrainings(userId);
	            return ResponseEntity.ok(completedTrainings);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }
	    
	   
	    @PostMapping("/logout")
	    public ResponseEntity<String> userLogout(HttpServletRequest request) {
	        // Log out the current user by invalidating the session
	        request.getSession().invalidate();
	        return ResponseEntity.ok("Logged out successfully.");
	    }   
	    
	    
	    @PostMapping("/completed-training")
	    public ResponseEntity<?> assignTrainingToUser(@RequestParam Long userId, @RequestParam Long trainingId) {
	    	UserTrainingCompleted userTrainingCompleted = userTrainingCompletedService.completedTraining(userId, trainingId);
	    	Map<Optional<Users>, Optional<Trainings>> map1 = new HashMap<>();
	    	Optional<Users> user = userRepository.findById(userId);
	    	Optional<Trainings> training = trainingRepository.findById(trainingId);
	    	map1.put(user, training);
	        return ResponseEntity.ok(map1);
	    }

	    @GetMapping("/allUsers")
	    public ResponseEntity<List<Users>> getAllUsers() {
	        try {
	            List<Users> allUsers = userRepository.findAll();
	            return ResponseEntity.ok(allUsers);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }
	    
	    
//	@GetMapping("/allassigntrainingtouser")
//	public  ResponseEntity<List<UserTrainingAssigned>>  getAssignTraings() {
//		try {
//			List<UserTrainingAssigned> allTrainings=userTrainingAssignedRepository.findAll();
//			return ResponseEntity.ok(allTrainings);
//		}catch(Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//		}
//	}
	    
	    @GetMapping("/allassigntrainingtouser/{userId}")
	    public ResponseEntity<List<UserTrainingAssigned>> getAssignTraingsByUserId(@PathVariable Long userId) {
	        try {
	            List<UserTrainingAssigned> userTrainings = userTrainingAssignedRepository.findByUserId(userId);
	            if (!userTrainings.isEmpty()) {
	                return ResponseEntity.ok(userTrainings);
	            } else {
	                return ResponseEntity.notFound().build();
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }
		
	

}
