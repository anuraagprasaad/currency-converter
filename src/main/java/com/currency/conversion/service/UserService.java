package com.currency.conversion.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.Errors;

import com.currency.conversion.exception.UserExitstException;
import com.currency.conversion.form.UserRegistrationForm;
import com.currency.conversion.model.CurrencyExchangeRateDetails;
import com.currency.conversion.model.User;

public interface UserService extends UserDetailsService {
	
	public User register(UserRegistrationForm data, Errors errors) throws UserExitstException;
	public List<CurrencyExchangeRateDetails> findTop10ByEmailOrderByIdDesc();
}
