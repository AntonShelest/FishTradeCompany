package com.bionic.edu.beans;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bionic.edu.entity.User;
import com.bionic.edu.service.UserService;

@Named
@Scope("session")
public class UserBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UserService userService;
	private User user = null;
	private String loginFailed;
	private String registerFailed;
	
	public UserBean(){
		user = new User();
		loginFailed = "false";
		registerFailed = "false";
	}
	
	public String login(){
		user = userService.login(user);
		if (user.getId() != 0)
			switch(user.getRoleId()){
			case "Customer": return "Customer";
			case "General Manager": return "GeneralManager";
			case "Cold Store Manager": return "ColdStoreManager";
			case "Accountant": return "Accountant";
			default: return "Customer";
			}
		else { 
			loginFailed = "true";
			return "login";
		}
	}
	
	public String register(){
		User usr = userService.find(user);
		if (usr != null){
			registerFailed = "true";
			return "Register";
		}
		else { 
			user = userService.register(user);
			return "Customer";
		}
	}
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getLoginFailed() {
		return loginFailed;
	}

	public void setLoginFailed(String loginFailed) {
		this.loginFailed = loginFailed;
	}

	public String getRegisterFailed() {
		return registerFailed;
	}

	public void setRegisterFailed(String registerFailed) {
		this.registerFailed = registerFailed;
	}
}
