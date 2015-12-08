package com.bionic.edu.entity;

import javax.persistence.*;

@Entity

public class SaleParcelItem {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private double price;
    private double weight;
    
    @ManyToOne
    @JoinColumn(name="saleParcelId")
    private SaleParcel saleParcel;
    
    @ManyToOne
    @JoinColumn(name="fishItemId")
    private FishItem fishItem;

    public SaleParcelItem(){   }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public SaleParcel getSaleParcel() {
		return saleParcel;
	}

	public void setSaleParcel(SaleParcel saleParcel) {
		this.saleParcel = saleParcel;
	}

	public FishItem getFishItem() {
		return fishItem;
	}

	public void setFishItem(FishItem fishItem) {
		this.fishItem = fishItem;
	}
}