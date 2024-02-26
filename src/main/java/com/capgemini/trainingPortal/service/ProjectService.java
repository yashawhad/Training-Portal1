package com.capgemini.trainingPortal.service;

import java.util.List;

import com.capgemini.trainingPortal.dto.ProjectDto;
import com.capgemini.trainingPortal.dto.TrainingDto;
import com.capgemini.trainingPortal.entity.Projects;
import com.capgemini.trainingPortal.entity.Trainings;

public interface ProjectService {
	Projects save(ProjectDto projectDto);

	List<ProjectDto> getAvailableForLoggedInUser(String grade);

}
