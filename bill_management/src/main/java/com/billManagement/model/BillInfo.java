package com.billManagement.model;

public class BillInfo {
	
	public BillInfo() {
		super();
	}
	public BillInfo(int amount, String currency, String chargeId, String email) {
		super();
		this.amount = amount;
		this.currency = currency;
		this.chargeId = chargeId;
		this.email = email;
	}
	
	
	int amount;
	String currency;
	String chargeId;
	String email;
	
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getChargeId() {
		return chargeId;
	}
	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
