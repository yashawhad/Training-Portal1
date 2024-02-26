package com.capgemini.trainingPortal.dto;

import org.apache.catalina.User;

import com.capgemini.trainingPortal.entity.Trainings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTrainingCompletedDto {
	private Long id;
	private User user;
	private Trainings training;
}
