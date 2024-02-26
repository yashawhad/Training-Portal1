package com.capgemini.trainingPortal.service.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.trainingPortal.entity.Trainings;
import com.capgemini.trainingPortal.entity.UserTrainingAssigned;
import com.capgemini.trainingPortal.entity.Users;
import com.capgemini.trainingPortal.repository.TrainingRepository;
import com.capgemini.trainingPortal.repository.UserRepository;
import com.capgemini.trainingPortal.repository.UserTrainingAssignedRepository;
import com.capgemini.trainingPortal.service.UserTrainingAssignedService;

@Service
public class UserTrainingAssignedServiceImpl implements UserTrainingAssignedService{

	@Autowired
	private UserTrainingAssignedRepository userTrainingRepository;
	@Autowired
	private  UserRepository userRepository;
	@Autowired
	private TrainingRepository trainingRepository;

	public void assignTraining(Long userId, Long trainingId) {
	    Users user = userRepository.findById(userId)
	                               .orElseThrow(() -> new RuntimeException("User not found"));
	    
	    Trainings training = trainingRepository.findById(trainingId)
	                                           .orElseThrow(() -> new RuntimeException("Training not found"));

	    // Check if the training is already assigned to the user
	    Optional<UserTrainingAssigned> existingAssignment = userTrainingRepository.findByUserAndTraining(user, training);
	    if (existingAssignment.isPresent()) {
	        throw new RuntimeException("Training is already assigned to the user");
	    }

	    UserTrainingAssigned userTraining = new UserTrainingAssigned();
	    userTraining.setUser(user);
	    userTraining.setTraining(training);
	    userTrainingRepository.save(userTraining);
	}

}
