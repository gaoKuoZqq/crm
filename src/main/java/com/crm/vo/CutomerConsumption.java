package com.crm.vo;

public class CutomerConsumption {
	String CustomerName;
	double Consumption;
	public CutomerConsumption(String customerName, double consumption) {
		super();
		CustomerName = customerName;
		Consumption = consumption;
	}
	public CutomerConsumption() {
		super();
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public double getConsumption() {
		return Consumption;
	}
	public void setConsumption(double consumption) {
		Consumption = consumption;
	}
	@Override
	public String toString() {
		return "CutomerConsumption [CustomerName=" + CustomerName + ", Consumption=" + Consumption + "]";
	}
	
}
