package com.bionic.edu.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.AccountantDao;
import com.bionic.edu.entity.Payment;
import com.bionic.edu.entity.SaleParcel;

@Named
public class AccountantServiceImp implements AccountantService{
	@Inject
	AccountantDao accountantDao;
	
	//User Story #16
	@Transactional
	public double registerPayment(Payment payment){
		double remainedAmount = payment.getAmount();
    	double required = 0;
    	SaleParcel saleParcel = payment.getSaleParcel();	
		if ((required = accountantDao.needToPay(saleParcel) - 
						accountantDao.alreadyPayed(saleParcel)) <= remainedAmount) {
			remainedAmount -= required;
			payment.setAmount(required);
			
			saleParcel.setStatus("P");
			if (saleParcel.getShipped() != null){
				saleParcel.setClosed(java.sql.Timestamp.valueOf(LocalDateTime.now()));
				saleParcel.setStatus("C");
			}
		}
		else {		
			if (remainedAmount + accountantDao.alreadyPayed(saleParcel) >=
					accountantDao.needToPay(saleParcel) * 
					saleParcel.getUser().getPrepaymentPercent()/100)
				saleParcel.setStatus("R");
			remainedAmount = 0;
		}	
		
		payment.setTstamp(Timestamp.valueOf(LocalDateTime.now()));
		accountantDao.registerPayment(payment);
		return remainedAmount;
	}
	
	//User Story #16
	public List<Payment> getAllPayments(){
		return accountantDao.getAllPayments();
	}
	
	//User Story #16
	public double getNeedToPay(SaleParcel saleParcel){
		return accountantDao.needToPay(saleParcel);
	}
	
	//User Story #16
	public double getAlreadyPayed(SaleParcel saleParcel){
		return accountantDao.alreadyPayed(saleParcel);
	}
	
	//User Story #17
	public List<SaleParcel> getCurrentParcels(){
		return accountantDao.getCurrentParcels();
	}
	
	//User Story #17
	@Transactional
	public SaleParcel approve4Ship(SaleParcel saleParcel){
		if (saleParcel.getStatus().equals("S"))
			saleParcel.setStatus("R");
		return accountantDao.approve4Ship(saleParcel);
	}
}
