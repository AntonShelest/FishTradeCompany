package com.bionic.edu.beans;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bionic.edu.entity.AdminService;
import com.bionic.edu.entity.FishItem;
import com.bionic.edu.entity.FishType;
import com.bionic.edu.entity.PurchaseParcel;
import com.bionic.edu.entity.ReportByDate;
import com.bionic.edu.entity.ReportByFish;
import com.bionic.edu.entity.TotalReport;
import com.bionic.edu.entity.User;
import com.bionic.edu.service.GeneralManagerService;

@Named("generalManager")
@Scope("session")
public class GeneralManagerBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private UserBean currentUser;
	private User user = null;
	private PurchaseParcel purchaseParcel = null;
	private FishItem fishItem = null;
	private List<PurchaseParcel> parcels = null;
	private Map<String,String> fishTypeValues = null;
	private String fishTypeId;
	
	private TotalReport totalReport = null;
	private Date reportBeginDate; 
	private Date reportEndDate;
	private String reportType;
	private List<ReportByDate> reportByDateList;
	private List<ReportByFish> reportByFishList;
	
	private List<User> customers;

	
	@Inject
	private GeneralManagerService generalManagerService;
	
	@Inject
	private AdminService adminService;
	
	public GeneralManagerBean() { }
	
	@PostConstruct
	public void init(){
		user = currentUser.getUser();
		purchaseParcel = new PurchaseParcel();
		fishItem = new FishItem();
		parcels = generalManagerService.getRegisteredPurchaseParcels();
		fishTypeValues = new LinkedHashMap<String,String>();
		for(FishType ft: generalManagerService.getAllFishTypes())
			fishTypeValues.put(ft.getName(),""+ft.getId());
		reportBeginDate = new Date();
		reportEndDate = new Date();
		customers = generalManagerService.getAllCustomers();
	}
	
	public UserBean getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserBean currentUser) {
		this.currentUser = currentUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<PurchaseParcel> getParcels() {
		return parcels;
	}

	public void setParcels(List<PurchaseParcel> parcels) {
		this.parcels = parcels;
	}

	public GeneralManagerService getGeneralManagerService() {
		return generalManagerService;
	}

	public void setGeneralManagerService(GeneralManagerService generalManagerService) {
		this.generalManagerService = generalManagerService;
	}
	
	public PurchaseParcel getPurchaseParcel() {
		return purchaseParcel;
	}

	public void setPurchaseParcel(PurchaseParcel purchaseParcel) {
		this.purchaseParcel = purchaseParcel;
	}

	public FishItem getFishItem() {
		return fishItem;
	}

	public void setFishItem(FishItem fishItem) {
		this.fishItem = fishItem;
	}

	public Map<String, String> getFishTypeValues() {
		return fishTypeValues;
	}

	public void setFishTypeValues(Map<String, String> fishTypeValues) {
		this.fishTypeValues = fishTypeValues;
	}

	public String getFishTypeId() {
		return fishTypeId;
	}

	public void setFishTypeId(String fishTypeId) {
		this.fishTypeId = fishTypeId;
	}
	
	public Date getReportBeginDate() {
		return reportBeginDate;
	}

	public void setReportBeginDate(Date reportBeginDate) {
		this.reportBeginDate = reportBeginDate;
	}

	public Date getReportEndDate() {
		return reportEndDate;
	}

	public void setReportEndDate(Date reportEndDate) {
		this.reportEndDate = reportEndDate;
	}

	public TotalReport getTotalReport() {
		return totalReport;
	}

	public void setTotalReport(TotalReport totalReport) {
		this.totalReport = totalReport;
	}

	public List<ReportByDate> getReportByDateList() {
		return reportByDateList;
	}

	public void setReportByDateList(List<ReportByDate> reportByDateList) {
		this.reportByDateList = reportByDateList;
	}

	public List<ReportByFish> getReportByFishList() {
		return reportByFishList;
	}

	public void setReportByFishList(List<ReportByFish> reportByFishList) {
		this.reportByFishList = reportByFishList;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public List<User> getCustomers() {
		return customers;
	}

	public void setCustomers(List<User> customers) {
		this.customers = customers;
	}

	public String editParcel(PurchaseParcel pp){
		purchaseParcel = pp;
		return "EditPurchaseParcel";
	}
	
	public String removeParcel(PurchaseParcel pp){
		parcels.remove(pp);
		return "GeneralManager";
	}
	
	public String registerParcel(){
		generalManagerService.savePurchaseParcel(purchaseParcel);
		purchaseParcel = new PurchaseParcel();
		return "GeneralManager";
	}
	
	public void refreshList(){
		parcels = generalManagerService.getRegisteredPurchaseParcels();
	}
	
	public void refreshCustomers(){
		customers = generalManagerService.getAllCustomers();
	}
	
	public String savePrepaymentInfo(User cust){
		generalManagerService.updateCustomer(cust);
		return "GeneralManager";
	}
	
	public String addItem(){
		fishItem.setPurchaseParcel(purchaseParcel);
		FishType ft = adminService.findFTById(Integer.parseInt(fishTypeId));
		fishItem.setFishType(ft);
		List<FishItem> fishItems = new ArrayList<>();
		if (purchaseParcel.getFishItems() != null)
			fishItems = (List<FishItem>) purchaseParcel.getFishItems();
		fishItems.add(fishItem);
		purchaseParcel.setFishItems(fishItems);
		fishItem = new FishItem();
		return "EditPurchaseParcel";
	}
	
	public String removeItem(FishItem fi){
		if (purchaseParcel.getFishItems() == null)
			return "EditPurchaseParcel";
		List<FishItem> fishItems = (List<FishItem>) purchaseParcel.getFishItems();
		fishItems.remove(fi);
		purchaseParcel.setFishItems(fishItems);
		return "EditPurchaseParcel";
	}
	
	public String generateReport(){
		Instant i_begin = Instant.ofEpochMilli(reportBeginDate.getTime());
		Instant i_end = Instant.ofEpochMilli(reportEndDate.getTime());
		LocalDateTime bDate = LocalDateTime.ofInstant(i_begin, ZoneId.systemDefault());
		LocalDateTime eDate = LocalDateTime.ofInstant(i_end, ZoneId.systemDefault());
		
		switch(reportType){
			case "byPeriod":
				totalReport = generalManagerService.generateTotalReport(bDate, eDate);
				return "ReportByPeriod";
			case "byDay":
				reportByDateList = generalManagerService.generateReportByDate(bDate, eDate);
				return "ReportByDate";
			case "byFish":
				reportByFishList = generalManagerService.generateReportByFish(bDate, eDate);
				return "ReportByFish";
			default: return "GeneralManager";
		}
	}

	public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "Login";
    }
}
