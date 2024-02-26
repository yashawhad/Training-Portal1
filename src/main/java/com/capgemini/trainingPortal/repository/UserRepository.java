package com.capgemini.trainingPortal.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.trainingPortal.entity.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	public Users findByEmail(String email);

//	public Users findByUsername(String loggedInUsername);
	
}
