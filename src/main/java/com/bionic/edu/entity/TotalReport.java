package com.bionic.edu.entity;

public class TotalReport {
	private double sum;
	private double weight;
	private double income;
	
	public TotalReport() { }
	
	public TotalReport(double sum, double weight, double income) { 
		this.sum = sum;
		this.weight = weight;
		this.income = income;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}
}
