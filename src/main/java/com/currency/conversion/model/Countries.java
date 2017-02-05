package com.currency.conversion.model;


public enum Countries {
	
	AU("Austria"), 
	BE("Belgium"), 
	CH("Switzerland"),
	DE("Germany"), 
	DK("Denmark"), 
	E2("Spain"), 
	FR("France"), 
	GB("Great Britain"),
	GR("Greece"), 
	IE("Ireland "), 
	IT("Italy"), 
	LU("Luxembourg"), 
	NL("Netherlands"), 
	PL("Poland"), 
	PT("Portugal"), 
	SE("Sweden");

	private final String name;

	Countries(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
 
}
