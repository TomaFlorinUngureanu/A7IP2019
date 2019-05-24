package com.accountManagement.model;

public class RegisterDetails {

	String email;
	String password;
	String phone_number;
	String country;
	String name;
	
	public RegisterDetails() {}
	
	public RegisterDetails(String email,String password,String phone_number,String country,String name)
	{
		this.email=email;
		this.password=password;
		this.phone_number=phone_number;
		this.country=country;
		this.name=name;
	}
	public RegisterDetails(RegisterDetails userDetails)
	{
		this.email=userDetails.email;
		this.password=userDetails.password;
		this.phone_number=userDetails.phone_number;
		this.country=userDetails.getCountry();
		this.name=userDetails.getName();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
