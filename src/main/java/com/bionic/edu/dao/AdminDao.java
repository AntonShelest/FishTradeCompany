package com.bionic.edu.dao;

import com.bionic.edu.entity.FishItem;
import com.bionic.edu.entity.FishType;
import com.bionic.edu.entity.Payment;
import com.bionic.edu.entity.PurchaseParcel;
import com.bionic.edu.entity.SaleParcel;
import com.bionic.edu.entity.SaleParcelItem;
import com.bionic.edu.entity.User;

public interface AdminDao {
	public FishItem findFIById(int id);
	public FishType findFTById(int id);
	public Payment findPById(int id);
	public PurchaseParcel findPPById(int id);
	public SaleParcel findSPById(int id);
	public SaleParcelItem findSPIById(int id);
	public User findUById(int id);
}
