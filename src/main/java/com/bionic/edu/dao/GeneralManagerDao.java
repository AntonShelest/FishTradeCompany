package com.bionic.edu.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.bionic.edu.entity.FishItem;
import com.bionic.edu.entity.FishType;
import com.bionic.edu.entity.PurchaseParcel;
import com.bionic.edu.entity.PurchaseTimestampResult;
import com.bionic.edu.entity.SaleParcelItem;
import com.bionic.edu.entity.TotalReport;
import com.bionic.edu.entity.User;

public interface GeneralManagerDao {
	//User Story #5-8
	public List<PurchaseParcel> getRegisteredPurchaseParcels();
	
	//User Story #5-8
	public PurchaseParcel savePurchaseParcel(PurchaseParcel purchaseParcel);
	
	//User Story #8
	public List<FishItem> getItemsOnSale();
	
	//User Story #8-9
	public FishItem updateFishItem(FishItem fishItem);
	
	//User Story #9
	public List<FishItem> getItemsRegistered4WriteOff();
	
	//User Story #10
	public List<User> getAllCustomers();
	
	//User Story #10
	public User updateCustomer(User user);
	
	//User Story #11
	public TotalReport generateTotalReport(LocalDateTime beginDate, LocalDateTime endDate);
	
	//User Story #11
	public PurchaseTimestampResult getReport4FishItem(SaleParcelItem saleParcelItem);
	
	//User Story #12
	public List<SaleParcelItem> getSaleItemsByPeriodAndFishType(
	LocalDateTime beginDate, LocalDateTime endDate, int fishTypeId);
	
	//User Story #5,6,12
	public List<FishType> getAllFishTypes();
}
