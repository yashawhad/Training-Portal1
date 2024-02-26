package com.capgemini.trainingPortal.service;

import java.util.List;

import com.capgemini.trainingPortal.dto.TrainingDto;
import com.capgemini.trainingPortal.dto.UserDto;
import com.capgemini.trainingPortal.dto.UserLoginDto;
import com.capgemini.trainingPortal.entity.Trainings;
import com.capgemini.trainingPortal.entity.Users;

public interface UserService {
	
	Users save(UserDto userDto);
	Users login(UserLoginDto userDto);
	Users  resetPassword(UserDto userDto);
	UserDto getCurrentUser();
	
	
	void updateTrainingProgress(Long userId, List<Long> completedTrainingIds);

    List<Long> getCompletedTrainings(Long userId);
	void generateGraphicalReport(Long userId);
	String getLoggedInUserGrade();
	List<TrainingDto> getAvailableTrainingsForLoggedInUser();
	
	
//	 List<Trainings> getUserTrainings(Long userId);
	


}
