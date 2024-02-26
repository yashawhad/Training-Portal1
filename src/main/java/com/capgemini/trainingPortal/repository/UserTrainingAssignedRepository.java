package com.capgemini.trainingPortal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.trainingPortal.entity.Trainings;
import com.capgemini.trainingPortal.entity.UserTrainingAssigned;
import com.capgemini.trainingPortal.entity.Users;

@Repository
public interface UserTrainingAssignedRepository extends JpaRepository<UserTrainingAssigned,Long>{

	boolean existsByUserAndTraining(Users user, Trainings training);

	Optional<UserTrainingAssigned> findByUserAndTraining(Users user, Trainings training);

	List<UserTrainingAssigned> findByUserId(Long userId);

}
