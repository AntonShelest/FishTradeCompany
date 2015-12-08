package com.bionic.edu.entity;

import java.util.Collection;

import javax.persistence.*;

@Entity

public class PurchaseParcel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String forSale;
    private java.sql.Timestamp created;   
    private java.sql.Timestamp arrived;   
	private String ship;
	@OneToMany(mappedBy="purchaseParcel", cascade=CascadeType.ALL)
	private Collection<FishItem> fishItems;

    public PurchaseParcel(){   }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.sql.Timestamp getCreated() {
		return created;
	}

	public void setCreated(java.sql.Timestamp created) {
		this.created = created;
	}

	public java.sql.Timestamp getArrived() {
		return arrived;
	}

	public void setArrived(java.sql.Timestamp arrived) {
		this.arrived = arrived;
	}

	public String getShip() {
		return ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	public Collection<FishItem> getFishItems() {
		return fishItems;
	}

	public void setFishItems(Collection<FishItem> fishItems) {
		this.fishItems = fishItems;
	}

	public String getForSale() {
		return forSale;
	}

	public void setForSale(String forSale) {
		this.forSale = forSale;
	}
}
