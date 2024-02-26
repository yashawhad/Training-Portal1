package com.capgemini.trainingPortal.dto;

import java.util.List;

import com.capgemini.trainingPortal.entity.Trainings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
	
	private Long id;
	private String name;
	private String description;

}
