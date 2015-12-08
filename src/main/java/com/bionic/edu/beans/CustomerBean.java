package com.bionic.edu.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bionic.edu.entity.FishItem;
import com.bionic.edu.entity.SaleParcel;
import com.bionic.edu.entity.SaleParcelItem;
import com.bionic.edu.entity.User;
import com.bionic.edu.service.ColdStoreManagerService;
import com.bionic.edu.service.CustomerService;

@Named("customer")
@Scope("session")
public class CustomerBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private UserBean currentUser;
	private User user = null;
	private SaleParcel parcel = null;
	private double itemWeight = 20;
	private List<FishItem> fishItems = null;
	
	@Inject
	private CustomerService customerService;
	
	@Inject
	private ColdStoreManagerService coldStoreManagerService;
	
	public CustomerBean() { }
	
	@PostConstruct
	public void init(){
		user = currentUser.getUser();
		parcel = new SaleParcel();
		parcel.setUser(user);
		fishItems = customerService.getFish4Sale();
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

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	public SaleParcel getParcel() {
		return parcel;
	}

	public void setParcel(SaleParcel parcel) {
		this.parcel = parcel;
	}
	
	public double getItemWeight() {
		return itemWeight;
	}

	public void setItemWeight(double itemWeight) {
		this.itemWeight = itemWeight;
	}

	public List<FishItem> getFishItems() {
		return fishItems;
	}

	public void setFishItems(List<FishItem> fishItems) {
		this.fishItems = fishItems;
	}

	public void addItem(FishItem fi){
		SaleParcelItem spi = new SaleParcelItem();
		spi.setFishItem(fi);
		spi.setPrice(fi.getPrice());
		spi.setSaleParcel(parcel);
		spi.setWeight(itemWeight);
		fi.setWeight(fi.getWeight() - itemWeight);
		
		
		List<SaleParcelItem> spiList = new ArrayList<SaleParcelItem>();
		if (parcel.getSaleParcelItems() != null){
			spiList = (List<SaleParcelItem>)parcel.getSaleParcelItems();
		}
		spiList.add(spi);
		parcel.setSaleParcelItems(spiList);
		itemWeight = 20;
	}
	
	public double getTotalWeight(){
		double sum = 0;
		if (parcel.getSaleParcelItems() != null)
			for(SaleParcelItem spi: parcel.getSaleParcelItems())
				sum += spi.getWeight();
		return sum;
	}
	
	public double getTotalPrice(){
		double sum = 0;
		if (parcel.getSaleParcelItems() != null)
			for(SaleParcelItem spi: parcel.getSaleParcelItems())
				sum += spi.getPrice()*spi.getWeight();
		return sum;
	}
	
	public void removeItem(SaleParcelItem spi){
		parcel.getSaleParcelItems().remove(spi);
	}
	
	public String submit(){
		for(FishItem fi: fishItems)
			coldStoreManagerService.updateFishItem(fi);
		customerService.sumbitSaleParcel(parcel);
		return "Customer";
	}
	
	public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "Login";
    }
}
