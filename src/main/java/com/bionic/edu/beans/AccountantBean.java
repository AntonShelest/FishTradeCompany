package com.bionic.edu.beans;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bionic.edu.entity.AdminService;
import com.bionic.edu.entity.Payment;
import com.bionic.edu.entity.SaleParcel;
import com.bionic.edu.entity.User;
import com.bionic.edu.service.AccountantService;

@Named("accountant")
@Scope("session")
public class AccountantBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private UserBean currentUser;
	private User user = null;
	private List<SaleParcel> parcels;
	private List<Payment> payments;
	private Map<String,String> saleParcels;
	private String saleParcelId;
	private double paymentAmount;
	private double needToPay;
	private double alreadyPayed;
	
	@Inject
	private AccountantService accountantService;
	
	@Inject
	private AdminService adminService;
	
	public AccountantBean() { }
	
	@PostConstruct
	public void init(){
		user = currentUser.getUser();
		parcels = accountantService.getCurrentParcels();
		saleParcels = new LinkedHashMap<String,String>();
		for(SaleParcel parcel: accountantService.getCurrentParcels())
			saleParcels.put(parcel.getId() + ": " + parcel.getUser().getName(),""+parcel.getId());
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
	
	public String doSomething(){
		return "Accountant";
	}

	public List<SaleParcel> getParcels() {
		return parcels;
	}

	public void setParcels(List<SaleParcel> parcels) {
		this.parcels = parcels;
	}

	public AccountantService getAccountantService() {
		return accountantService;
	}

	public void setAccountantService(AccountantService accountantService) {
		this.accountantService = accountantService;
	}
	
	public List<Payment> getPayments() {
		return payments;
	}
	
	public Map<String, String> getSaleParcels() {
		return saleParcels;
	}

	public void setSaleParcels(Map<String, String> saleParcels) {
		this.saleParcels = saleParcels;
	}

	public String getSaleParcelId() {
		return saleParcelId;
	}

	public void setSaleParcelId(String saleParcelId) {
		this.saleParcelId = saleParcelId;
	}

	public String refreshPayments(){
		payments = accountantService.getAllPayments();
		return "Accountant";
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	
	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public double getNeedToPay() {
		return needToPay;
	}

	public void setNeedToPay(double needToPay) {
		this.needToPay = needToPay;
	}

	public double getAlreadyPayed() {
		return alreadyPayed;
	}

	public void setAlreadyPayed(double alreadyPayed) {
		this.alreadyPayed = alreadyPayed;
	}

	public String registerPayment(Payment payment){
		accountantService.registerPayment(payment);
		return "Payment";
	}

	public String refreshList(){
		parcels = accountantService.getCurrentParcels();
		return "Accountant";
	}
	
	public void refreshTableRow(SaleParcel saleParcel){
		needToPay = accountantService.getNeedToPay(saleParcel);
		alreadyPayed = accountantService.getAlreadyPayed(saleParcel);
	}
	
	public String approve4Ship(SaleParcel saleParcel){
		accountantService.approve4Ship(saleParcel);
		return "Accountant";
	}
	
	public String addPayment(){
		Payment payment = new Payment();
		payment.setSaleParcel(adminService.findSPById(
				Integer.parseInt(saleParcelId)));
		payment.setAmount(paymentAmount);
		accountantService.registerPayment(payment);
		return "Payment";
	}
	
	public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "Login";
    }
}