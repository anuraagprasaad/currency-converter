package com.currency.conversion.test.cucumber;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Date;

import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.currency.conversion.Application;
import com.currency.conversion.form.UserRegistrationForm;
import com.currency.conversion.model.Countries;
import com.currency.conversion.model.User;
import com.currency.conversion.service.CurrencyService;
import com.currency.conversion.service.OpenExchangeRateService;
import com.currency.conversion.service.UserService;

import cucumber.api.Format;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class,Application.class, UserService.class,CurrencyService.class,OpenExchangeRateService.class}, loader = SpringApplicationContextLoader.class)
@TestPropertySource(locations = "classpath:test.properties")
@WebIntegrationTest("server.port:0")
public class UserRegstrationAndLoginStepDefs {

	@Autowired(required = true)
	private AuthenticationManager authenticationManager;

	@Autowired(required = true)
	private UserService userService;
	
	@Autowired
	private DataSource ds;
	@Value("${local.server.port}")
	private int port;

	private UserRegistrationForm data = new UserRegistrationForm();

	private User user;

	private Authentication loggedIn;

	private Exception exception;

	private Errors errors;

	@Before
	public void init() {
		errors = new BeanPropertyBindingResult(data, "");
	}

	@Given("^email (.*)$")
	public void email(String email) {
		data.setEmail(email);
	}

	@Given("^country (.*)$")
	public void country(String value) {
		data.setCountry(Countries.valueOf(value));
	}

	@Given("^street (.*)$")
	public void street(String value) {
		data.setStreet(value);
	}

	@Given("^city (.*)$")
	public void city(String value) {
		data.setCity(value);
	}

	@Given("^zip (.*)$")
	public void zip(String value) {
		data.setZip(value);
	}

	@Given("^password (.*)$")
	public void password(String password) {
		data.setPassword(password);
	}

	@Given("^dob (.*)$")
	public void dob(@Format("yyyy-MM-dd") Date date) throws Throwable {
		data.setDob(date);
	}

	@When("^I try to register$")
	public void i_try_to_register() throws Throwable {
		user = userService.register(data, errors);
	}

	@Then("^I should be registered$")
	public void registered() throws Throwable {
		assertThat(user, notNullValue());
		assertThat(user.getEmail(), is(data.getEmail()));
	}

	@When("^I try to login$")
	public void i_try_to_login() {
		Authentication authentication = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
		try {
			loggedIn = authenticationManager.authenticate(authentication);
		} catch (AuthenticationException e) {
			exception = e;
		}
	}
	
	@Then("^I should be logged in$")
	public void i_should_be_logged_in() throws Throwable {
		//assertThat(loggedIn, notNullValue());
		assertThat(exception, nullValue());
	}

	@Then("^I should get error messages$")
	public void i_should_get_error_messages() throws Throwable {
		assertTrue(errors.hasErrors());
	}

	@Then("^I should get error for field (.*)$")
	public void i_should_get_error_for_field(String field) throws Throwable {
		assertTrue(errors.hasFieldErrors(field));
	}

	@Then("^I should fail to log in$")
	public void i_should_fail_to_log_in() throws Throwable {
		assertThat(loggedIn, nullValue());
		assertThat(exception, notNullValue());
		assertThat(exception, instanceOf(BadCredentialsException.class));
	}

}
