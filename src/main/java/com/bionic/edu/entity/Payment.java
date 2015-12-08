package com.bionic.edu.entity;

import javax.persistence.*;

@Entity

public class Payment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private double amount;
    private java.sql.Timestamp tstamp;
    
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="saleParcelId")
    private SaleParcel saleParcel;

    public Payment(){   }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public java.sql.Timestamp getTstamp() {
		return tstamp;
	}

	public void setTstamp(java.sql.Timestamp tstamp) {
		this.tstamp = tstamp;
	}

	public SaleParcel getSaleParcel() {
		return saleParcel;
	}

	public void setSaleParcel(SaleParcel saleParcel) {
		this.saleParcel = saleParcel;
	}
}