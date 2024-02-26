package com.capgemini.trainingPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.trainingPortal.entity.Projects;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, Long>{

	Projects findByName(String name);
}
