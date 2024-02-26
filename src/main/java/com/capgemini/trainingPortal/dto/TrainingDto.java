package com.capgemini.trainingPortal.dto;

import java.util.Set;

import com.capgemini.trainingPortal.entity.Projects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDto {
	
    private Long id;
    private String name;
    private String description;
    private Set<String> grades; // Add field for grades
    private Projects projects;
}
