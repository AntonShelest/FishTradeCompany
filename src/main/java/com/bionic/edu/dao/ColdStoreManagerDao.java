package com.bionic.edu.dao;

import java.util.List;

import com.bionic.edu.entity.FishItem;
import com.bionic.edu.entity.PurchaseParcel;
import com.bionic.edu.entity.SaleParcel;

public interface ColdStoreManagerDao {
	
	//User Story #13
	public List<PurchaseParcel> getParcelsToArrive();
	
	//User Story #13
	public PurchaseParcel registerParcelArrival(PurchaseParcel purchaseParcel);
	
	//User Story #14
	public List<SaleParcel> getParcelsToShip();
	
	//User Story #14
	public SaleParcel registerShipment(SaleParcel saleParcel);
	
	//User Story #15
	public FishItem updateFishItem(FishItem fishItem);
	
	//User Story #15
	public List<FishItem> getCurrentFishItems();
}
