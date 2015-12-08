package com.bionic.edu.dao;

import java.util.List;

import com.bionic.edu.entity.FishItem;
import com.bionic.edu.entity.SaleParcel;

public interface CustomerDao {
	//User Story #1
	public List<FishItem> getFish4Sale();
	
	//User Story #2
	public SaleParcel submitSaleParcel(SaleParcel saleParcel);
}
