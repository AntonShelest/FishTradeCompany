package com.bionic.edu.service;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.GeneralManagerDao;
import com.bionic.edu.entity.FishItem;
import com.bionic.edu.entity.FishType;
import com.bionic.edu.entity.PurchaseParcel;
import com.bionic.edu.entity.PurchaseTimestampResult;
import com.bionic.edu.entity.ReportByDate;
import com.bionic.edu.entity.ReportByFish;
import com.bionic.edu.entity.SaleParcelItem;
import com.bionic.edu.entity.TotalReport;
import com.bionic.edu.entity.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;	
import java.util.List;

import javax.inject.Inject;

@Named
public class GeneralManagerServiceImp implements GeneralManagerService{
	@Inject
	GeneralManagerDao generalManagerDao;
	
	//User Story #5,6,12
	public List<FishType> getAllFishTypes(){
		return generalManagerDao.getAllFishTypes();
	}
	
	//User Story #5-8
	public List<PurchaseParcel> getRegisteredPurchaseParcels(){
		return generalManagerDao.getRegisteredPurchaseParcels();
	}
	
	//User Story #5-8
	@Transactional
	public PurchaseParcel savePurchaseParcel(PurchaseParcel purchaseParcel){
		purchaseParcel.setCreated(Timestamp.valueOf(LocalDateTime.now()));
		String forSale = "Y";
		for(FishItem fi:purchaseParcel.getFishItems())
			if (fi.getPrice() <= 0 || fi.getWeight() <= 0) forSale = "N";
		purchaseParcel.setForSale(forSale);
		return generalManagerDao.savePurchaseParcel(purchaseParcel);
	}
	
	//User Story #8
	public List<FishItem> getItemsOnSale(){
		return generalManagerDao.getItemsOnSale();
	}
	
	//User Story #8
	@Transactional
	public FishItem updateFishItem(FishItem fishItem){
		return generalManagerDao.updateFishItem(fishItem);
	}
	
	//User Story #9
	public List<FishItem> getItemsRegistered4WriteOff(){
		return generalManagerDao.getItemsRegistered4WriteOff();
	}
	
	//User Story #9
	@Transactional
	public FishItem writeOffFishItem(FishItem fishItem){
		fishItem.setPrice(-1);
		return generalManagerDao.updateFishItem(fishItem);
	}
	
	//User Story #10
	public List<User> getAllCustomers(){
		return generalManagerDao.getAllCustomers();
	}
	
	//User Story #10
	@Transactional
	public User updateCustomer(User user){
		return generalManagerDao.updateCustomer(user);
	}
	
	//User Story #11
	public TotalReport generateTotalReport(LocalDateTime beginDate, LocalDateTime endDate){
		return generalManagerDao.generateTotalReport(beginDate, endDate);
	}
	
	//User Story #12
	public List<ReportByFish> generateReportByFish(LocalDateTime beginDate, LocalDateTime endDate){
		List<ReportByFish> reportByFishList = new ArrayList<ReportByFish>();
		double sum = 0;
		double weight = 0;
		double income = 0;
		double days = 0;
		double primaryCost = 0;
		double revenue = 0;
		
		for(FishType ft: generalManagerDao.getAllFishTypes()){
			List<SaleParcelItem> saleItems = 
					generalManagerDao.
					getSaleItemsByPeriodAndFishType(beginDate, endDate, ft.getId());
			
			//Define total sum, weight and income
			for(SaleParcelItem saleParcelItem: saleItems){
				PurchaseTimestampResult ptResult = generalManagerDao.getReport4FishItem(saleParcelItem);
				
				days = (saleParcelItem
						.getSaleParcel()
						.getShipped()
						.getTime() - 
					ptResult.getTimestamp().getTime())/(1000*60*60*24);
				primaryCost = ptResult.getPrice()*saleParcelItem.getWeight() + 
						days*saleParcelItem.getWeight()*FishItem.PRIMARY_COST_CONSTANT;
				
				revenue = saleParcelItem.getPrice()*saleParcelItem.getWeight();
				
				sum += revenue;
				weight += saleParcelItem.getWeight();
				income += (revenue - primaryCost);
			}
			
			reportByFishList.add(new ReportByFish(ft.getName(), sum, weight, income));
			sum = 0;
			weight = 0;
			income = 0;
		}
	return reportByFishList;
	}
	
	//User Story #12
	public List<ReportByDate> generateReportByDate(LocalDateTime beginDate, LocalDateTime endDate){
		List<ReportByDate> reportByDateList = new ArrayList<ReportByDate>();
		LocalDateTime currDate = beginDate;
		while (currDate.isBefore(endDate)){		
			if (!(currDate = beginDate.plusDays(1).truncatedTo(ChronoUnit.DAYS)).isBefore(endDate))
				currDate = endDate;
			
			TotalReport tr = generalManagerDao.generateTotalReport(beginDate, currDate);
			
			if (tr.getSum() != 0)
				reportByDateList.add(new ReportByDate(beginDate, currDate,
						tr.getSum(), tr.getWeight(), tr.getIncome()));	
			
			beginDate = currDate;
		}
		return reportByDateList;
	}
}

