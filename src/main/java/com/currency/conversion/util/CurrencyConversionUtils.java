package com.currency.conversion.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.currency.conversion.form.CurrencyForm;
import com.currency.conversion.form.UserRegistrationForm;
import com.currency.conversion.model.CurrencyExchangeRateDetails;
import com.currency.conversion.model.User;

@Component
public class CurrencyConversionUtils {
	
	private final ModelMapper modelMapper;
	
	public CurrencyConversionUtils() {
		this.modelMapper = new ModelMapper();
		this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
	}

	public User convertUserRegistrationFormToUserObject(UserRegistrationForm userRegistrationFrom) {
		return this.modelMapper.map(userRegistrationFrom, User.class);
	}

	public UserRegistrationForm convertUserObjectToUserRegistrationFormToUser(User user) {
		return  this.modelMapper.map(user, UserRegistrationForm.class);
	}
	
	public CurrencyExchangeRateDetails convertCurrencyFormToCurrencyObject(CurrencyForm form) {
		return this.modelMapper.map(form, CurrencyExchangeRateDetails.class);
	}

	public CurrencyForm convertCurrencyObjectToCurrencyForm(CurrencyExchangeRateDetails conversion) {
		return this.modelMapper.map(conversion, CurrencyForm.class);
	}
	
	
	
}
