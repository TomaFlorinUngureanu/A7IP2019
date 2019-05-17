package com.rating.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ratings")
public class RatingForm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	private int id;
	@Column(name="email_driver")
	private String emailDriver;
	@Column(name="rating")
	private int rating;
	@Column(name="id_package")
	private int idPackage;
	
	public RatingForm() {}


	public RatingForm(int id, String emailDriver, int rating, int id_package) {
		super();
		this.id = id;
		this.emailDriver = emailDriver;
		this.rating = rating;
		this.idPackage = id_package;
	}
	
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

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getIdPackage() {
		return idPackage;
	}

	public void setIdPackage(int idPackage) {
		this.idPackage = idPackage;
	}
	
}
