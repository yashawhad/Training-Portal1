package com.capgemini.trainingPortal.service;

import com.capgemini.trainingPortal.entity.UserTrainingCompleted;

public interface UserTrainingCompletedService {

	UserTrainingCompleted completedTraining(Long userId, Long trainingId);

}
