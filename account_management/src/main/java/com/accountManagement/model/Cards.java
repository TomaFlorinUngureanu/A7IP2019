package com.accountManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cards")
public class Cards {
	
	public Cards() {
	}

	public Cards(int id, String emailSender, String cardNumber, String mm, String yy, String cvv, String country,
			String zipCode) {
		super();
		this.id = id;
		this.emailSender = emailSender;
		this.cardNumber = cardNumber;
		this.mm = mm;
		this.yy = yy;
		this.cvv = cvv;
		this.country = country;
		this.zipCode = zipCode;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	int id;
	
	@Column(name="email_sender")
	String emailSender;
	
	@Column(name="card_number")
	String cardNumber;
	
	@Column(name="MM")
	String mm;
	
	@Column(name="YY")
	String yy;
	
	@Column(name="CVV")
	String cvv;
	
	@Column(name="country")
	String country;
	
	@Column(name="zip_code")
	String zipCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmailSender() {
		return emailSender;
	}

	public void setEmailSender(String emailSender) {
		this.emailSender = emailSender;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	public String getYy() {
		return yy;
	}

	public void setYy(String yy) {
		this.yy = yy;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
