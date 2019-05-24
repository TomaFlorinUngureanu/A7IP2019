package com.packages.model;

public class ModifyPackageInformation {

	private int id;
	private String status;
	private int pin;

	public ModifyPackageInformation() {	
	}

	public ModifyPackageInformation(int id,String status,int pin) {
		super();
		this.id=id;
		this.pin=pin;
		this.status = status;
	}
	
	public ModifyPackageInformation(int id,String status) {
		super();
		this.id=id;
		this.pin=-1;
		this.status = status;
	}
	
	public ModifyPackageInformation(String status) {
		super();
		this.id=0;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return this.id;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public void setId(int id) {
		this.id = id;
	}	
}
