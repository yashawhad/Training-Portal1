package com.capgemini.trainingPortal.service;

import java.util.List;

import com.capgemini.trainingPortal.dto.TrainingDto;
import com.capgemini.trainingPortal.entity.Trainings;

public interface TrainingService {
	
	Trainings save(TrainingDto trainingDto);

	List<TrainingDto> getAvailableTrainings(String userGrade);

	List<TrainingDto> getAvailableForLoggedInUser(String grade);

	

	

	

}
