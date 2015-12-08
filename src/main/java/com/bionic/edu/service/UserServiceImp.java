package com.bionic.edu.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.UserDao;
import com.bionic.edu.entity.User;
import com.bionic.edu.security.PasswordHash;

@Named
public class UserServiceImp implements UserService {
	@Inject
	UserDao userDao;
	
	//User Story #4
	public User find(User user){
		return userDao.find(user);
	}
	
	//User Story #4
	@Transactional
    public User register(User user){
		try{
		user.setPassword(PasswordHash.createHash(user.getPassword()));
		} catch (Exception e) {}
		user.setPrepaymentPercent(100);
		user.setRoleId("Customer");
    	return userDao.register(user);
    }
	
	//User Story #21
    public User login(User user){
    	User usr = userDao.login(user.getLogin());
    	if (usr != null) {
    		try{
    			if (PasswordHash.validatePassword(user.getPassword(), 
    											   usr.getPassword())){
    				usr.setPassword("");
    				return usr;
    			}
    		}
    		catch(NoSuchAlgorithmException e){
    			System.out.println("NoSuchAlgorithmException"); 
    			return user;
    		}
    		catch(InvalidKeySpecException e){
    			System.out.println("InvalidKeySpecException"); 
    			return user;
    		}
    	}
    	return user;
    }
}
