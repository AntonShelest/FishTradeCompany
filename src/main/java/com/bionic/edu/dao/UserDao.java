package com.bionic.edu.dao;

import com.bionic.edu.entity.User;

public interface UserDao {
	//User Story #4
	public User find(User user);
	
	//User Story #4
	public User register(User user);
	
	//User Story #21
	public User login(String login);
}
