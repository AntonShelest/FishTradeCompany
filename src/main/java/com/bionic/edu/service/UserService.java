package com.bionic.edu.service;

import com.bionic.edu.entity.User;

public interface UserService {
	//User Story #4
	public User find(User user);
	
	//User Story #4
	public User register(User user);
		
	//User Story #21
	public User login(User user);
}
