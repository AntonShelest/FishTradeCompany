package com.bionic.edu.service;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebService;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.CustomerDao;
import com.bionic.edu.entity.FishItem;
import com.bionic.edu.entity.SaleParcel;
import com.bionic.edu.entity.User;

@Named
@WebService(endpointInterface = "com.bionic.edu.service.CustomerService")
public class CustomerServiceImp implements CustomerService{
	@Inject
    private CustomerDao customerDao;
	
	//User Story #1
	public List<FishItem> getFish4Sale(){
		return customerDao.getFish4Sale();
	}
	
	//User Story #2
	@Transactional
	public SaleParcel sumbitSaleParcel(SaleParcel saleParcel){
    	User user = saleParcel.getUser();
	    saleParcel.setSubmitted(Timestamp.valueOf(LocalDateTime.now()));
	    saleParcel.setStatus("S");
    	if (user.getPrepaymentPercent() == 0)
    		saleParcel.setStatus("R");
		return customerDao.submitSaleParcel(saleParcel);
	}
}
