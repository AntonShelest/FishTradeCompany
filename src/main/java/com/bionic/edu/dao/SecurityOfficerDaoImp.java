package com.bionic.edu.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bionic.edu.entity.User;

@Repository
public class SecurityOfficerDaoImp implements SecurityOfficerDao {
	@PersistenceContext
	EntityManager em;
	
	//User story #19
	public User saveUser(User user){
		if (user.getId() == 0)
			em.persist(user);
		else 
			em.merge(user);
		return user;
	}
}
