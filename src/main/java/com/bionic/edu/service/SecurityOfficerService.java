package com.bionic.edu.service;

import com.bionic.edu.entity.User;

public interface SecurityOfficerService {
	
	//User story #19
	public User saveUser(User user);
	
	//User story #20
	public User blockUser(User user);
}
