package com.bionic.edu.entity;

public interface AdminService {
	public FishItem findFIById(int id);
	public FishType findFTById(int id);
	public Payment findPById(int id);
	public PurchaseParcel findPPById(int id);
	public SaleParcel findSPById(int id);
	public SaleParcelItem findSPIById(int id);
	public User findUById(int id);
}
