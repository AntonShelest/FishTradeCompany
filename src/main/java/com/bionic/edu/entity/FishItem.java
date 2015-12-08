package com.bionic.edu.entity;

import javax.persistence.*;

@Entity

public class FishItem {
	public static final int PRIMARY_COST_CONSTANT = 500;
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private double purchaseWeight;
    private double weight;
    private double purchasePrice;
    private double price;
    private java.sql.Timestamp writtenOff;
    
    @ManyToOne
    @JoinColumn(name="fishTypeId")
    private FishType fishType;
    
    @ManyToOne
    @JoinColumn(name="purchaseParcelId")
    private PurchaseParcel purchaseParcel;

    public FishItem(){   }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPurchaseWeight() {
		return purchaseWeight;
	}

	public void setPurchaseWeight(double purchaseWeight) {
		this.purchaseWeight = purchaseWeight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public FishType getFishType() {
		return fishType;
	}

	public void setFishType(FishType fishType) {
		this.fishType = fishType;
	}

	public PurchaseParcel getPurchaseParcel() {
		return purchaseParcel;
	}

	public void setPurchaseParcel(PurchaseParcel purchaseParcel) {
		this.purchaseParcel = purchaseParcel;
	}

	public java.sql.Timestamp getWrittenOff() {
		return writtenOff;
	}

	public void setWrittenOff(java.sql.Timestamp writtenOff) {
		this.writtenOff = writtenOff;
	}

	public static int getPrimaryCostConstant() {
		return PRIMARY_COST_CONSTANT;
	}

	public String toString(){
		return  "Fish Name: " + fishType.getName() +
				" price: " + price +
				" amount: " + weight +
				" description: " + fishType.getDescription();
	}
}