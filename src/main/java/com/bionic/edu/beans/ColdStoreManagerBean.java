package com.bionic.edu.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bionic.edu.entity.FishItem;
import com.bionic.edu.entity.PurchaseParcel;
import com.bionic.edu.entity.SaleParcel;
import com.bionic.edu.entity.User;
import com.bionic.edu.service.ColdStoreManagerService;

@Named("coldStoreManager")
@Scope("session")
public class ColdStoreManagerBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private UserBean currentUser;
	private User user = null;
	private PurchaseParcel purchaseParcel = null;
	private FishItem fishItem = null;
	private List<PurchaseParcel> parcelsToArrive = null;
	private List<SaleParcel> parcelsToShip = null;
	
	@Inject
	private ColdStoreManagerService coldStoreManagerService;
	
	public ColdStoreManagerBean() { }
	
	@PostConstruct
	public void init(){
		user = currentUser.getUser();
		purchaseParcel = new PurchaseParcel();
		fishItem = new FishItem();
		parcelsToArrive = coldStoreManagerService.getParcelsToArrive();
		parcelsToShip = coldStoreManagerService.getParcelsToShip();
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

	public List<PurchaseParcel> getParcelsToArrive() {
		return parcelsToArrive;
	}

	public void setParcelsToArrive(List<PurchaseParcel> parcelsToArrive) {
		this.parcelsToArrive = parcelsToArrive;
	}

	public List<SaleParcel> getParcelsToShip() {
		return parcelsToShip;
	}

	public void setParcelsToShip(List<SaleParcel> parcelsToShip) {
		this.parcelsToShip = parcelsToShip;
	}
	
	public String registerParcelArrival(PurchaseParcel pp){
		purchaseParcel = pp;
		return "EditArrivedParcel";
	}
	
	public String updateParcel(){
		coldStoreManagerService.registerParcelArrival(purchaseParcel);
		return "ColdStoreManager";
	}
	
	public void refreshParcelsToShip(){
		parcelsToShip = coldStoreManagerService.getParcelsToShip();
	}
	
	public String shipParcel(SaleParcel saleParcel){
		coldStoreManagerService.registerShipment(saleParcel);
		return "Parcel4Ship";
	}
	
	public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "Login";
    }
}
