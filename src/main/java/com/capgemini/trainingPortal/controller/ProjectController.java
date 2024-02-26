package com.capgemini.trainingPortal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.trainingPortal.dto.ProjectDto;
import com.capgemini.trainingPortal.dto.TrainingDto;
import com.capgemini.trainingPortal.entity.Projects;
import com.capgemini.trainingPortal.repository.ProjectRepository;
import com.capgemini.trainingPortal.service.ProjectService;
@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ProjectRepository pr;
	
	@PostMapping("/register-project")
    public Projects registerProject(@RequestBody ProjectDto projectDto) {
        Projects project = projectService.save(projectDto);
        return project;
    }
	
	@GetMapping("/available-projects")
    public ResponseEntity<?> getAvailableProjectsForUser() {
		
		return ResponseEntity.ok(pr.findAll());
    }
	
	@GetMapping("/available-projects-for-logged-in-user")
    public ResponseEntity<List<ProjectDto>> getAvailableTrainingsForLoggedInUser(@RequestParam("grade") String grade) {
        List<ProjectDto> availableTrainings = projectService.getAvailableForLoggedInUser(grade);
        return ResponseEntity.ok(availableTrainings);
    }

}
