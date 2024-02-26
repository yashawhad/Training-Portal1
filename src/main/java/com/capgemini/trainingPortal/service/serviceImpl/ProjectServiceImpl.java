package com.capgemini.trainingPortal.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.trainingPortal.dto.ProjectDto;
import com.capgemini.trainingPortal.entity.Projects;
import com.capgemini.trainingPortal.exception.ProjectAlreadyRegisteredException;
import com.capgemini.trainingPortal.repository.ProjectRepository;
import com.capgemini.trainingPortal.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Projects save(ProjectDto projectDto) {
        Projects existingProject = projectRepository.findByName(projectDto.getName());
        if (existingProject != null) {
            throw new ProjectAlreadyRegisteredException("Project is already registered.");
        }
        Projects project = new Projects();
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());

        // Saving the project first to ensure it's managed
        project = projectRepository.save(project);

        return project;
    }

    @Override
    public List<ProjectDto> getAvailableForLoggedInUser(String grade) {
        List<Projects> projectsList = projectRepository.findAll(); // Retrieve projects based on grade
        return projectsList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
 
    private ProjectDto convertToDto(Projects project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setDescription(project.getDescription());
        return projectDto;
    }
}
