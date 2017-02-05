package com.currency.conversion.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.currency.conversion.dao.UserHistoryRepository;
import com.currency.conversion.dao.UserRepository;
import com.currency.conversion.exception.UserExitstException;
import com.currency.conversion.form.UserRegistrationForm;
import com.currency.conversion.model.CurrencyExchangeRateDetails;
import com.currency.conversion.model.User;
import com.currency.conversion.service.UserService;
import com.currency.conversion.util.CurrencyConversionUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserHistoryRepository historyRepository;
	
	@Autowired
	private Validator validator;
	
	/*@Autowired
	private PasswordEncoder passwordEncoder;
*/
	public UserServiceImpl() {
		
	}
	
	public UserServiceImpl(Validator validator, UserRepository repository, UserHistoryRepository historyRepository) {
		this.validator = validator;
		this.repository = repository;
		this.historyRepository = historyRepository;
	}
	
	@Override
	public User register(UserRegistrationForm form, Errors errors) throws UserExitstException {
		logger.info("===register===");
		/*Objects.requireNonNull(form);
		Objects.requireNonNull(form.getUsername());
		Objects.requireNonNull(form.getAddress());
		Objects.requireNonNull(form.getStreet());
		Objects.requireNonNull(form.getZip());
		Objects.requireNonNull(form.getCity());
		Objects.requireNonNull(form.getCountry());
		Objects.requireNonNull(form.getPassword());*/
		
		// Validate the data transfer object
		validator.validate(form, errors);
		
		// Check if user exists
		if (repository.findByEmail(form.getEmail()) != null) {
			//errors.rejectValue("email", "username.exists", "User already exists");
			throw new UserExitstException();
		}

		if (errors.hasErrors()) {
			logger.error("Registration failed with errors  {}", errors);
		}

		CurrencyConversionUtils currencyConversionUtils = new CurrencyConversionUtils();
		User user = currencyConversionUtils.convertUserRegistrationFormToUserObject(form);
		user.setPassword(form.getPassword());
		user.setRole("ROLE_USER");

		user = repository.save(user);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = repository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		return user;
	}

	@Override
	public List<CurrencyExchangeRateDetails> findTop10ByEmailOrderByIdDesc() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return historyRepository.findTop10ByEmailOrderByIdDesc(email);
	}

}
