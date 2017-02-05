package com.currency.conversion.validator;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.currency.conversion.form.UserRegistrationForm;

@Component
public class UserRegistratonFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserRegistrationForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserRegistrationForm user = (UserRegistrationForm) target;
		validateEmail(user, errors);
		validateAge(user, errors);
		validateStreet(user, errors);
		validateCity(user, errors);
		validateZip(user, errors);
	}

	protected void validateEmail(UserRegistrationForm user, Errors errors) {
		if (user.getEmail() == null) {
			return;
		}
		// RFC-822 validation
		InternetAddress emailAddr;
		try {
			emailAddr = new InternetAddress(user.getEmail());
			emailAddr.validate();
		} catch (AddressException e) {
			errors.rejectValue("email", "email.invalid");
		}
	}

	protected void validateAge(UserRegistrationForm user, Errors errors) {
		if (user.getDob() == null) {
			return;
		}
				
		LocalDate today = LocalDate.now();
		int years = Period.between(Instant.ofEpochMilli(user.getDob().getTime()).atZone(ZoneId.systemDefault())
				.toLocalDateTime().toLocalDate(), today).getYears();
		if (years < 12) {
			errors.rejectValue("dob", "birthdate.invalid");
		}
	}
	
	protected void validateStreet(UserRegistrationForm user, Errors errors) {
		if (user.getStreet() == null || user.getStreet().trim().equals("")) {
			errors.rejectValue("street", "street.required");
		}
		if(user.getStreet() != null && user.getStreet().trim().length() > 100)
			errors.rejectValue("street", "street.length");
	}
	
	protected void validateCity(UserRegistrationForm user, Errors errors) {
		if (user.getCity() == null || user.getCity().trim().equals("")) {
			errors.rejectValue("city", "city.required");
		}
		if(user.getCity() != null && user.getCity().trim().length() > 100)
			errors.rejectValue("city", "city.length");
		
		if(user.getCity() != null && !user.getCity().trim().equals("")) {
			String regex = "^[a-zA-Z\\- ]+$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(user.getCity());
			if(!matcher.matches())
				errors.rejectValue("city", "city.invalid");
		}
		
	}
	
	protected void validateZip(UserRegistrationForm user, Errors errors) {
		if (user.getZip() == null || user.getZip().trim().equals("")) {
			errors.rejectValue("zip", "zip.required");
		}
		if(user.getZip() != null && !user.getZip().trim().equals("")) {
			String regex = "(?i)^[a-z0-9][a-z0-9\\- ]{0,10}[a-z0-9]$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(user.getZip());
			if(!matcher.matches())
				errors.rejectValue("zip", "zip.invalid");
		}
		
	}
}