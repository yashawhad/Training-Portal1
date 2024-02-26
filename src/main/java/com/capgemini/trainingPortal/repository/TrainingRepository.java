package com.capgemini.trainingPortal.repository;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.trainingPortal.dto.TrainingDto;
import com.capgemini.trainingPortal.entity.Trainings;
@Repository
public interface TrainingRepository extends JpaRepository<Trainings, Long>{

	Trainings findByName(String name);
//	Trainings findByGrades(String userGrade);
//	User findById(long userId);
	List<Trainings> findAllByGradesContaining(String userGrade);
//	List<TrainingDto> findByRequiredGrade(String grade);

}
