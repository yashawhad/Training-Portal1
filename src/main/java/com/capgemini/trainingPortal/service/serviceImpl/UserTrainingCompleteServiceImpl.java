package com.capgemini.trainingPortal.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.trainingPortal.entity.Trainings;
import com.capgemini.trainingPortal.entity.UserTrainingCompleted;
import com.capgemini.trainingPortal.entity.Users;
import com.capgemini.trainingPortal.repository.TrainingRepository;
import com.capgemini.trainingPortal.repository.UserRepository;
import com.capgemini.trainingPortal.repository.UserTrainingCompletedRepository;
import com.capgemini.trainingPortal.repository.UserTrainingAssignedRepository;
import com.capgemini.trainingPortal.service.UserTrainingCompletedService;

@Service
public class UserTrainingCompleteServiceImpl implements UserTrainingCompletedService{

	@Autowired
	private UserTrainingCompletedRepository userTrainingCompletedRepository;
	@Autowired
	private  UserRepository userRepository;
	@Autowired
	private TrainingRepository trainingRepository;
    @Autowired
	private UserTrainingAssignedRepository userTraingRepository;
	
	
	public UserTrainingCompleted completedTraining(Long userId, Long trainingId) {
	    Users user = userRepository.findById(userId)
	                                .orElseThrow(() -> new RuntimeException("User not found"));
	    Trainings training = trainingRepository.findById(trainingId)
	                                           .orElseThrow(() -> new RuntimeException("Training not found"));

	    // Check if the user has been assigned the specified training
	    boolean isAssigned = userTraingRepository.existsByUserAndTraining(user, training);
	    if (!isAssigned) {
	        throw new RuntimeException("Training has not been assigned to the user");
	    }

	    UserTrainingCompleted userTrainingCompleted = new UserTrainingCompleted();
	    userTrainingCompleted.setUser(user);
	    userTrainingCompleted.setTraining(training);
	    return userTrainingCompletedRepository.save(userTrainingCompleted);
	}


}
