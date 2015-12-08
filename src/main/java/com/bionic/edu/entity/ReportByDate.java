package com.bionic.edu.entity;

import java.time.LocalDateTime;

public class ReportByDate {
	private LocalDateTime beginDTime;
	private LocalDateTime endDTime;
	private double sum;
	private double weight;
	private double income;
	
	public ReportByDate() { }
	
	public ReportByDate(LocalDateTime beginDTime, LocalDateTime endDTime,
			double sum, double weight, double income) { 
		this.beginDTime = beginDTime;
		this.endDTime = endDTime;
		this.sum = sum;
		this.weight = weight;
		this.income = income;
	}

	public LocalDateTime getBeginDTime() {
		return beginDTime;
	}

	public void setBeginDTime(LocalDateTime beginDTime) {
		this.beginDTime = beginDTime;
	}

	public LocalDateTime getEndDTime() {
		return endDTime;
	}

	public void setEndDTime(LocalDateTime endDTime) {
		this.endDTime = endDTime;
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
