package com.currency.conversion.form;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.currency.conversion.model.Countries;

public class UserRegistrationForm {
	@NotNull
	private String email;
	@NotNull
	@Size(min = 8, max = 50)
	private String password;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	@Past
	private Date dob;	
	@NotNull
	private String street;
	@NotNull
	private String zip;
	@NotNull
	private String city;
	@NotNull
	private Countries country;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Countries getCountry() {
		return this.country;
	}

	public void setCountry(Countries country) {
		this.country = country;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZipCode(String zip) {
		this.zip = zip;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
		
}
