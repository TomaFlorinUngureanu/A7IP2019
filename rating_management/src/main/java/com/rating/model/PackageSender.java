package com.rating.model;

public class PackageSender {

	public PackageSender(int id, String emailDriver, String emailSender, String status) {
		super();
		this.id = id;
		this.emailDriver = emailDriver;
		this.emailSender = emailSender;
		this.status = status;
	}
	public PackageSender() {
	}
	int id;
	String emailDriver;
	String emailSender;
	String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmailDriver() {
		return emailDriver;
	}
	public void setEmailDriver(String emailDriver) {
		this.emailDriver = emailDriver;
	}
	public String getEmailSender() {
		return emailSender;
	}
	public void setEmailSender(String emailSender) {
		this.emailSender = emailSender;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
