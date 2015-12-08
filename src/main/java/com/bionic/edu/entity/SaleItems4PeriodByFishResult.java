package com.bionic.edu.entity;

public class SaleItems4PeriodByFishResult {
	private String name;
	private double sum;
	private double weight;
	private double income;
	
	public SaleItems4PeriodByFishResult() { }
	
	public SaleItems4PeriodByFishResult(String name, double sum, double weight, double income) { 
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
