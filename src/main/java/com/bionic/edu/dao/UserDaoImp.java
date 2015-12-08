package com.bionic.edu.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.edu.entity.User;

@Repository
public class UserDaoImp implements UserDao {
	@PersistenceContext
	EntityManager em;
	
	//User Story #4
	public User find(User user){
		String sql = "SELECT u FROM User u WHERE u.login = :login";
		TypedQuery<User> query = em.createQuery(sql, User.class);
		query.setParameter("login", user.getLogin());
		try {return query.getSingleResult();}
		catch(NoResultException e){return null;}
	}
	
	//User Story #4
	public User register(User user){
		if (user.getId() == 0)
			em.persist(user);
		else em.merge(user);
		return user;
	}
	
	//User Story #21
	public User login(String login){
		String sql = "SELECT u FROM User u " +
				"WHERE u.login = :login AND (u.isBlocked IS NULL) OR " +
				"(u.isBlocked <> 'Y')";
		TypedQuery<User> query = em.createQuery(sql, User.class);
		query.setParameter("login", login);
		try {return query.getSingleResult();}
		catch(NoResultException e){return null;}
	}
}
