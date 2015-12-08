package com.bionic.edu.entity;

import java.sql.Timestamp;

public class PurchaseTimestampResult {
	private int id;
	private double price;
	private java.sql.Timestamp timestamp;
	
	PurchaseTimestampResult() { }

	public PurchaseTimestampResult(int id, double price, Timestamp timestamp) {
		this.id = id;
		this.price = price;
		this.timestamp = timestamp;
	}

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

	public java.sql.Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(java.sql.Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
