package com.capgemini.trainingPortal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.trainingPortal.dto.TrainingDto;
import com.capgemini.trainingPortal.dto.UserDto;
import com.capgemini.trainingPortal.entity.Trainings;
import com.capgemini.trainingPortal.repository.TrainingRepository;
import com.capgemini.trainingPortal.service.TrainingService;
import com.capgemini.trainingPortal.service.UserService;

@RestController
@RequestMapping("/training")
@CrossOrigin(origins = "http://localhost:4200")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;
    
    @Autowired
    private TrainingRepository trainingRepository;
    
    @Autowired
    private UserService userService;
    
    
    // Gourav 
    @PostMapping("/register-training")
    public ResponseEntity<String> registerTraining(@RequestBody TrainingDto trainingDto) {
        try {
            trainingService.save(trainingDto);
            return ResponseEntity.ok("Training module registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register training module.");
        }
    }

    
    // Gourav  I want to view all available trainings for my assigned grade
    @GetMapping("/available-trainings")
    public ResponseEntity<?> getAvailableTrainingsForUser() {
		
		return ResponseEntity.ok(trainingRepository.findAll());
    }
    
    @GetMapping("/available-for-logged-in-user")
    public ResponseEntity<List<TrainingDto>> getAvailableTrainingsForLoggedInUser(@RequestParam("grade") String grade) {
        List<TrainingDto> availableTrainings = trainingService.getAvailableForLoggedInUser(grade);
        return ResponseEntity.ok(availableTrainings);
    }

    
   


}


