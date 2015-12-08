package com.bionic.edu.dao;

import java.util.List;

import com.bionic.edu.entity.Payment;
import com.bionic.edu.entity.SaleParcel;

public interface AccountantDao {

	//User Story #16
	public Payment registerPayment(Payment payment);
	
	//User Story #16
	public List<Payment> getAllPayments();
	
	//User Story #16
	public double needToPay(SaleParcel saleParcel);
	
	//User Story #16
	public double alreadyPayed(SaleParcel saleParcel);
	
	//User Story #17
	public List<SaleParcel> getCurrentParcels();
	
	//User Story #17
	public SaleParcel approve4Ship(SaleParcel saleParcel);
}
