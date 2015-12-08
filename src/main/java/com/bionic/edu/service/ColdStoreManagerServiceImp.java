package com.bionic.edu.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.ColdStoreManagerDao;
import com.bionic.edu.entity.FishItem;
import com.bionic.edu.entity.PurchaseParcel;
import com.bionic.edu.entity.SaleParcel;

@Named
public class ColdStoreManagerServiceImp implements ColdStoreManagerService {
	@Inject
	ColdStoreManagerDao coldStoreManagerDao;
	
	//User Story #13
	public List<PurchaseParcel> getParcelsToArrive(){
		return coldStoreManagerDao.getParcelsToArrive();
	}
	
	//User Story #13
	@Transactional
	public PurchaseParcel registerParcelArrival(PurchaseParcel purchaseParcel){
		purchaseParcel.setArrived(Timestamp.valueOf(LocalDateTime.now()));
		String forSale = "Y";
		for(FishItem fi:purchaseParcel.getFishItems())
			if (fi.getPrice() <= 0 || fi.getWeight() <= 0) forSale = "N";
		purchaseParcel.setForSale(forSale);
		return coldStoreManagerDao.registerParcelArrival(purchaseParcel);
	}
	
	//User Story #14
	public List<SaleParcel> getParcelsToShip(){
		return coldStoreManagerDao.getParcelsToShip();
	}
	
	//User Story #14
	@Transactional
	public SaleParcel registerShipment(SaleParcel saleParcel){
		if (saleParcel.getStatus().equals("R")){
			saleParcel.setStatus("H");
			saleParcel.setShipped(Timestamp.valueOf(LocalDateTime.now()));
			return coldStoreManagerDao.registerShipment(saleParcel);
		}
		return saleParcel;
	}
	
	//User Story #15
	@Transactional
	public FishItem updateFishItem(FishItem fishItem){
		return coldStoreManagerDao.updateFishItem(fishItem);
	}
	
	//User Story #15
	public List<FishItem> getCurrentFishItems(){
		return coldStoreManagerDao.getCurrentFishItems();
	}
}
