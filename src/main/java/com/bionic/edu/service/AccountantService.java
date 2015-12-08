package com.bionic.edu.service;

import java.util.List;

import com.bionic.edu.entity.Payment;
import com.bionic.edu.entity.SaleParcel;

public interface AccountantService {
	
	//User Story #16
	public double registerPayment(Payment payment);
	
	//User Story #16
	public List<Payment> getAllPayments();
	
	//User Story #16
	public double getNeedToPay(SaleParcel saleParcel);
	
	//User Story #16
	public double getAlreadyPayed(SaleParcel saleParcel);
	
	//User Story #17
	public List<SaleParcel> getCurrentParcels();
	
	//User Story #17
	public SaleParcel approve4Ship(SaleParcel saleParcel);
}
