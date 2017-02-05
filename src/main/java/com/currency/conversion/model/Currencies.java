package com.currency.conversion.model;

public enum Currencies {
	
	EUR("Euro"),
	USD("United States Dollar"),
	GBP("British Pound Sterling"),
	NZD("New Zealand Dollar"),
	AUD("Australian Dollar"),
	JPY("Japanese Yen"),
	CAD("Canadian Dollar"),	
	CHF("Swiss Franc");

	private final String name;

	Currencies(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
