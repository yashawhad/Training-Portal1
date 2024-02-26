package com.capgemini.trainingPortal.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.trainingPortal.dto.TrainingDto;
import com.capgemini.trainingPortal.entity.Trainings;
import com.capgemini.trainingPortal.repository.TrainingRepository;
import com.capgemini.trainingPortal.service.TrainingService;
 
@Service
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;
 
    @Override
    public Trainings save(TrainingDto trainingDto) {
        // Your existing code to check if training already exists
 
        // Create a new Trainings object with assigned grades
        Trainings trainings = new Trainings();
        trainings.setName(trainingDto.getName());
        trainings.setDescription(trainingDto.getDescription());
        trainings.setGrades(trainingDto.getGrades());
        trainings.setProjects(trainingDto.getProjects());
 
        // Save the training module
        return trainingRepository.save(trainings);
    }
 
    @Override
    public List<TrainingDto> getAvailableTrainings(String userGrade) {
		return null;
    }
 
    @Override
    public List<TrainingDto> getAvailableForLoggedInUser(String grade) {
        List<Trainings> trainingsList = trainingRepository.findAll(); // Retrieve all trainings
        return trainingsList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
 
 
    private TrainingDto convertToDto(Trainings trainings) {
        TrainingDto trainingDto = new TrainingDto();
        trainingDto.setId(trainings.getId());
        trainingDto.setName(trainings.getName());
        trainingDto.setDescription(trainings.getDescription());
        trainingDto.setProjects(trainings.getProjects());
        return trainingDto;
    }
 
 
    
 
 
   
 
    

}

