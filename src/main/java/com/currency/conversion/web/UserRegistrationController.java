package com.currency.conversion.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.currency.conversion.exception.UserExitstException;
import com.currency.conversion.form.UserRegistrationForm;
import com.currency.conversion.model.Countries;
import com.currency.conversion.service.UserService;
import com.currency.conversion.validator.UserRegistratonFormValidator;

@Controller
public class UserRegistrationController extends WebMvcConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);

	private UserService registrationService;
	
	@Autowired
	private UserRegistratonFormValidator userRegistrationValidator;

	@Autowired
	public UserRegistrationController(UserService registrationService,UserRegistratonFormValidator userRegistrationValidator) {
		this.registrationService = registrationService;
		this.userRegistrationValidator = userRegistrationValidator;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.userRegistrationValidator);
	}

	@ModelAttribute("countries")
	protected Countries[] getCountryValues() {
		return Countries.values();
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new UserRegistrationForm());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String handleUserCreateForm(@Valid @ModelAttribute("user") UserRegistrationForm user,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "signup";
		}
		try {
			this.registrationService.register(user, bindingResult);		
		}
		catch (UserExitstException e) {
			logger.info("Username already exists", e);
			bindingResult.rejectValue("email", "username.exists");
			return "signup";
		}		

		return "redirect:/";
	}

	public UserService getRegistrationService() {
		return registrationService;
	}

	public void setRegistrationService(UserService registrationService) {
		this.registrationService = registrationService;
	}

	public UserRegistratonFormValidator getUserRegistrationValidator() {
		return userRegistrationValidator;
	}

	public void setUserRegistrationValidator(UserRegistratonFormValidator userRegistrationValidator) {
		this.userRegistrationValidator = userRegistrationValidator;
	}
	
}
