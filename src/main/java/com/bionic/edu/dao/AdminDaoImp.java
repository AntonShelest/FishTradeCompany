package com.bionic.edu.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.edu.entity.FishItem;
import com.bionic.edu.entity.FishType;
import com.bionic.edu.entity.Payment;
import com.bionic.edu.entity.PurchaseParcel;
import com.bionic.edu.entity.SaleParcel;
import com.bionic.edu.entity.SaleParcelItem;
import com.bionic.edu.entity.User;

@Repository
public class AdminDaoImp implements AdminDao{
	@PersistenceContext
	EntityManager em;
	
	public FishItem findFIById(int id){
		String sql = "SELECT fi FROM FishItem fi WHERE fi.id = :id";
		TypedQuery<FishItem> query = em.createQuery(sql, FishItem.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	public FishType findFTById(int id){
		String sql = "SELECT ft FROM FishType ft WHERE ft.id = :id";
		TypedQuery<FishType> query = em.createQuery(sql, FishType.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	public Payment findPById(int id){
		String sql = "SELECT p FROM Payment p WHERE p.id = :id";
		TypedQuery<Payment> query = em.createQuery(sql, Payment.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	public PurchaseParcel findPPById(int id){
		String sql = "SELECT pp FROM PurchaseParcel pp WHERE pp.id = :id";
		TypedQuery<PurchaseParcel> query = em.createQuery(sql, PurchaseParcel.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	public SaleParcel findSPById(int id){
		String sql = "SELECT sp FROM SaleParcel sp WHERE sp.id = :id";
		TypedQuery<SaleParcel> query = em.createQuery(sql, SaleParcel.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	public SaleParcelItem findSPIById(int id){
		String sql = "SELECT spi FROM SaleParcelItem spi WHERE spi.id = :id";
		TypedQuery<SaleParcelItem> query = em.createQuery(sql, SaleParcelItem.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	public User findUById(int id){
		String sql = "SELECT u FROM User u WHERE u.id = :id";
		TypedQuery<User> query = em.createQuery(sql, User.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
}
