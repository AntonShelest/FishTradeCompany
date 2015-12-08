package com.bionic.edu.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.edu.entity.FishItem;
import com.bionic.edu.entity.FishType;
import com.bionic.edu.entity.PurchaseParcel;
import com.bionic.edu.entity.PurchaseTimestampResult;
import com.bionic.edu.entity.SaleParcelItem;
import com.bionic.edu.entity.TotalReport;
import com.bionic.edu.entity.User;

@Repository
public class GeneralManagerDaoImp implements GeneralManagerDao {
	@PersistenceContext
	EntityManager em;
	
	//User Story #5-8
	public List<PurchaseParcel> getRegisteredPurchaseParcels(){
		String sql = "SELECT pp FROM PurchaseParcel pp WHERE pp.forSale = 'N'";
		TypedQuery<PurchaseParcel> query = em.createQuery(sql, PurchaseParcel.class);
		try{return query.getResultList();}
		catch(Exception e) {return new ArrayList<PurchaseParcel>();}
	}
	
	//User Story #5-8
	public PurchaseParcel savePurchaseParcel(PurchaseParcel purchaseParcel){
		if (purchaseParcel.getId() == 0){
			purchaseParcel.setForSale("N");
			em.persist(purchaseParcel);
		}
		else em.merge(purchaseParcel);
		return purchaseParcel;	
	}	
	
	//User Story #8
	public List<FishItem> getItemsOnSale(){
		String sql = "SELECT fi FROM FishItem fi WHERE fi.price > 0";
		TypedQuery<FishItem> query = em.createQuery(sql, FishItem.class);
		try{return query.getResultList();}
		catch(Exception e) {return new ArrayList<FishItem>();}
	}
	
	//User Story #8
	public FishItem updateFishItem(FishItem fishItem){
		em.merge(fishItem);
		return fishItem;
	}
	
	//User Story #9
	public List<FishItem> getItemsRegistered4WriteOff(){
		String sql = "SELECT fi FROM FishItem fi WHERE "
				+ "NOT writtenOff IS NULL AND fi.price >= 0";
		TypedQuery<FishItem> query = em.createQuery(sql, FishItem.class);
		try{return query.getResultList();}
		catch(Exception e) {return new ArrayList<FishItem>();}
	}
	
	//User Story #10
	public List<User> getAllCustomers(){
		String sql = "SELECT u FROM User u WHERE u.roleId = 'Customer'";
		TypedQuery<User> query = em.createQuery(sql, User.class);
		try{return query.getResultList();}
		catch(Exception e) {return new ArrayList<User>();}
	}
	
	//User Story #10
	public User updateCustomer(User user){
		em.merge(user);
		return user;
	}
	
	//User Story #11
	public TotalReport generateTotalReport(LocalDateTime beginDate, LocalDateTime endDate){
		//Retrieve all shipped Sale Parcel Items
		String sql = "SELECT spi FROM SaleParcelItem spi WHERE "
				+ "spi.saleParcel.shipped >= :beginDate AND spi.saleParcel.shipped	 <= :endDate "
				+ "AND NOT spi.saleParcel.shipped IS NULL";

		TypedQuery<SaleParcelItem> query = em.createQuery(sql, SaleParcelItem.class);
		query.setParameter("beginDate", java.sql.Timestamp.valueOf(beginDate));
		query.setParameter("endDate", java.sql.Timestamp.valueOf(endDate));
		
		try{return generateTotalReport4SaleParcelItems(query.getResultList());}
		catch(Exception e) {return new TotalReport();}
	}
	//User Story #12
	public List<FishType> getAllFishTypes(){
		String sql = "SELECT ft FROM FishType ft";
		TypedQuery<FishType> query = em.createQuery(sql, FishType.class);	
		try{return query.getResultList();}
		catch(Exception e) {return new ArrayList<FishType>();}
	}
	
	//User Story #12
	public List<SaleParcelItem> getSaleItemsByPeriodAndFishType(LocalDateTime beginDate, LocalDateTime endDate, int fishTypeId){
		String sql = "SELECT spi FROM SaleParcelItem spi WHERE "
				+ "spi.saleParcel.shipped >= :beginDate AND "
				+ "spi.saleParcel.shipped <= :endDate AND "
				+ "NOT spi.saleParcel.shipped IS NULL AND "
				+ "spi.fishItem.fishType.id = :id";

		TypedQuery<SaleParcelItem> query = em.createQuery(sql, SaleParcelItem.class);
		query.setParameter("beginDate", java.sql.Timestamp.valueOf(beginDate));
		query.setParameter("endDate", java.sql.Timestamp.valueOf(endDate));
		query.setParameter("id", fishTypeId);
		
		try{return query.getResultList();}
		catch(Exception e) {return new ArrayList<SaleParcelItem>();}
	}
	
	//User Story #11
	public PurchaseTimestampResult getReport4FishItem(SaleParcelItem saleParcelItem){
		String sql = "SELECT new com.bionic.edu.entity.PurchaseTimestampResult("
				   + "fi.id, fi.purchasePrice, fi.purchaseParcel.arrived) "
				   + "FROM FishItem fi WHERE fi.id = :id";
		TypedQuery<PurchaseTimestampResult> query = em.createQuery(sql, PurchaseTimestampResult.class);
		query.setParameter("id", saleParcelItem.getFishItem().getId());
		try{return query.getSingleResult();}
		catch(Exception e) {return null;}
	}
	
	private TotalReport generateTotalReport4SaleParcelItems(List<SaleParcelItem> saleParcelItems){
		//Define total sum, weight and income
		double sum = 0;
		double weight = 0;
		double income = 0;
		double days = 0;
		double primaryCost = 0;
		double revenue = 0;
		for(SaleParcelItem saleParcelItem: saleParcelItems){
			String sql = "SELECT new com.bionic.edu.entity.PurchaseTimestampResult("
					   + "fi.id, fi.purchasePrice, fi.purchaseParcel.arrived) "
					   + "FROM FishItem fi WHERE fi.id = :id";
			TypedQuery<PurchaseTimestampResult> query = em.createQuery(sql, PurchaseTimestampResult.class);
			query.setParameter("id", saleParcelItem.getFishItem().getId());
			
			PurchaseTimestampResult ptResult;
			try{ptResult = query.getSingleResult();}
			catch(Exception e) {return null;}
			
			days = (saleParcelItem
					.getSaleParcel()
					.getShipped()
					.getTime() - 
				ptResult.getTimestamp().getTime())/(1000*60*60*24);
			primaryCost = ptResult.getPrice()*saleParcelItem.getWeight() + 
					days*saleParcelItem.getWeight()*FishItem.PRIMARY_COST_CONSTANT;
			
			revenue = saleParcelItem.getPrice()*saleParcelItem.getWeight();
			
			sum += revenue;
			weight += saleParcelItem.getWeight();
			income += (revenue - primaryCost);
		}
		
		return new TotalReport(sum, weight, income);
	}
}
