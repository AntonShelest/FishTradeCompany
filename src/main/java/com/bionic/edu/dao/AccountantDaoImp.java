package com.bionic.edu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.edu.entity.Payment;
import com.bionic.edu.entity.SaleParcel;

@Repository
public class AccountantDaoImp implements AccountantDao {
	@PersistenceContext
	EntityManager em;
	
	//User Story #16
	public Payment registerPayment(Payment payment){
		em.merge(payment);
		return payment;
	}
	
	//User Story #16
	public double needToPay(SaleParcel saleParcel){
		String sql = "SELECT SUM(DISTINCT spi.weight*spi.price) FROM SaleParcelItem spi "
				+ "WHERE spi.saleParcel.id = :id";
		TypedQuery<Double> query = em.createQuery(sql, Double.class);
		query.setParameter("id", saleParcel.getId());
		try{return query.getSingleResult();}
		catch(NullPointerException e){return 0;}
	}
	
	//User Story #16
	public List<Payment> getAllPayments(){
		String sql = "SELECT p FROM Payment p ORDER BY p.tstamp DESC";
		TypedQuery<Payment> query = em.createQuery(sql, Payment.class);
		try{return query.getResultList();}
		catch(NullPointerException e){return null;}
	}
	
	//User Story #16
	public double alreadyPayed(SaleParcel saleParcel){
		String sql = "SELECT SUM(DISTINCT p.amount) FROM Payment p "
				+ "WHERE p.saleParcel.id = :id";
		TypedQuery<Double> query = em.createQuery(sql, Double.class);
		query.setParameter("id", saleParcel.getId());
		try{return query.getSingleResult();}
		catch(NullPointerException e){return 0;}
	}
	
	//User Story #17
	public List<SaleParcel> getCurrentParcels(){
		String sql = "SELECT sp FROM SaleParcel sp "
				+ "WHERE sp.status <> 'C'";
		TypedQuery<SaleParcel> query = em.createQuery(sql, SaleParcel.class);
		try{return query.getResultList();}
		catch(NullPointerException e){return null;}
	}
	
	//User Story #17
	public SaleParcel approve4Ship(SaleParcel saleParcel){
		em.merge(saleParcel);
		return saleParcel;
	}
}
