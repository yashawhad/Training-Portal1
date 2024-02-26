package com.capgemini.trainingPortal.service;

import java.util.List;

import com.capgemini.trainingPortal.dto.AdminDto;
import com.capgemini.trainingPortal.dto.UserDto;
import com.capgemini.trainingPortal.entity.Admins;
import com.capgemini.trainingPortal.entity.Users;

public interface AdminService {

	Admins adminLogin(AdminDto adminDto);
	Admins save(AdminDto adminDto);
	void assignTrainingsToUser(Long userId, List<Long> trainingIds);
}

