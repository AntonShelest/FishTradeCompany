package com.bionic.edu.dao;

import org.springframework.stereotype.Repository;

import com.bionic.edu.entity.FishItem;
import com.bionic.edu.entity.SaleParcel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class CustomerDaoImp implements CustomerDao {
	@PersistenceContext
	EntityManager em;
	
	//User Story #1
	public List<FishItem> getFish4Sale(){
		String txt = "SELECT DISTINCT fi "
				+ "FROM FishItem fi "
				+ "WHERE fi.price > 0 AND fi.weight > 0";
		TypedQuery<FishItem> query = em.createQuery(txt, FishItem.class);
		try{return query.getResultList();}
		catch(Exception e) {return new ArrayList<FishItem>();}
	}
	
	//User Story #1
	public SaleParcel submitSaleParcel(SaleParcel saleParcel){
		em.persist(saleParcel);
		return saleParcel;
	}
}
