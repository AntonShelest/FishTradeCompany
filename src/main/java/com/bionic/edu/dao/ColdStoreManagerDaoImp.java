package com.bionic.edu.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.edu.entity.FishItem;
import com.bionic.edu.entity.PurchaseParcel;
import com.bionic.edu.entity.SaleParcel;

@Repository
public class ColdStoreManagerDaoImp implements ColdStoreManagerDao {
	@PersistenceContext
	EntityManager em;
	
	//User Story #13
	public List<PurchaseParcel> getParcelsToArrive(){
		String sql = "SELECT pp FROM PurchaseParcel pp WHERE "
				+ "pp.arrived IS NULL";
		TypedQuery<PurchaseParcel> query = em.createQuery(sql, PurchaseParcel.class);
		try{return query.getResultList();} 
		catch(Exception e) {return new ArrayList<PurchaseParcel>();}
	}
	
	//User Story #13
	public PurchaseParcel registerParcelArrival(PurchaseParcel purchaseParcel){
		em.merge(purchaseParcel);
		return purchaseParcel;
	}
	
	//User Story #14
	public List<SaleParcel> getParcelsToShip(){
		String sql = "SELECT sp FROM SaleParcel sp WHERE "
				+ "sp.status = 'R'";
		TypedQuery<SaleParcel> query = em.createQuery(sql, SaleParcel.class);
		try{return query.getResultList();}
		catch(Exception e) {return new ArrayList<SaleParcel>();}
	}
	
	//User Story #14
	public SaleParcel registerShipment(SaleParcel saleParcel){
		em.merge(saleParcel);
		return saleParcel;
	}
	
	//User Story #15
	public FishItem updateFishItem(FishItem fishItem){
		em.merge(fishItem);
		return fishItem;
	}
	
	//User Story #15
	public List<FishItem> getCurrentFishItems(){
		String sql = "SELECT fi FROM FishItem fi WHERE fi.price >= 0";
		TypedQuery<FishItem> query = em.createQuery(sql, FishItem.class);
		try{return query.getResultList();}
		catch(Exception e) {return new ArrayList<FishItem>();}
	}
}
