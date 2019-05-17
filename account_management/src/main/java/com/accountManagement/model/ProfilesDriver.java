package com.accountManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profile_data_driver")
public class ProfilesDriver {
	
	@Id
	@Column(name = "email")
	private String email;
	@Column(name="name")
	private String name;
	@Column(name = "phone_number")
	private String phone_number;
	@Column(name="country")
	private String country;
	
	
	public ProfilesDriver() {

	}

	public ProfilesDriver(ProfilesDriver profile) {
		this.email = profile.email;
		this.phone_number = profile.phone_number;
		this.country=profile.country;
		this.name=profile.name;
	}
	public ProfilesDriver(String email,String name, String phone_number,String country) {
		this.email = email;
		this.phone_number = phone_number;
		this.country=country;
		this.name=name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
