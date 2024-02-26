package com.capgemini.trainingPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.trainingPortal.entity.Admins;

public interface AdminRepository extends JpaRepository<Admins, Long> {
	public Admins findByEmail(String email);
}
