package com.bionic.edu.entity;

import javax.inject.Inject;
import javax.inject.Named;

import com.bionic.edu.dao.AdminDao;

@Named
public class AdminServiceImp implements AdminService {
	@Inject
	AdminDao adminDao;
	
	public FishItem findFIById(int id){
		return adminDao.findFIById(id);
	}
	
	public FishType findFTById(int id){
		return adminDao.findFTById(id);
	}
	
	public Payment findPById(int id){
		return adminDao.findPById(id);
	}
	
	public PurchaseParcel findPPById(int id){
		return adminDao.findPPById(id);
	}
	
	public SaleParcel findSPById(int id){
		return adminDao.findSPById(id);
	}
	
	public SaleParcelItem findSPIById(int id){
		return adminDao.findSPIById(id);
	}
	
	public User findUById(int id){
		return adminDao.findUById(id);
	}
}
