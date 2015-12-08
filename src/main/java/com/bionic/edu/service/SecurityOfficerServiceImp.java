package com.bionic.edu.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.SecurityOfficerDao;
import com.bionic.edu.entity.User;

@Named
public class SecurityOfficerServiceImp implements SecurityOfficerService {
	@Inject
	SecurityOfficerDao securityOfficerDao;
	
	//User story #19
	@Transactional
	public User saveUser(User user){
		return securityOfficerDao.saveUser(user);
	}
	
	//User story #20
	@Transactional
	public User blockUser(User user){
		user.setIsBlocked("Y");
		return securityOfficerDao.saveUser(user);
	}
}
