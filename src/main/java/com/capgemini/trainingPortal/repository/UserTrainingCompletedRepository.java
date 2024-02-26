package com.capgemini.trainingPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.trainingPortal.entity.UserTrainingAssigned;
import com.capgemini.trainingPortal.entity.UserTrainingCompleted;

@Repository
public interface UserTrainingCompletedRepository extends JpaRepository<UserTrainingCompleted,Long>{

}
