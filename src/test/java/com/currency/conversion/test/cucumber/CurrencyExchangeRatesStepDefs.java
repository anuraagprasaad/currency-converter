package com.currency.conversion.test.cucumber;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import com.currency.conversion.service.CurrencyService;
import com.currency.conversion.service.OpenExchangeRateService;
import com.currency.conversion.service.UserService;

import cucumber.api.Format;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes = TestConfig.class)
@TestPropertySource(locations = "classpath:test.properties")
public class CurrencyExchangeRatesStepDefs {

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private UserService userService;

	@Autowired
	private OpenExchangeRateService openExchangeRateService;

	private String from;

	private String to;

	private BigDecimal result;

	private int count;

	private BigDecimal amount;

	@Before
	public void init() {
		SecurityContextHolder.getContext().setAuthentication(new TestingAuthenticationToken("testuser@test.com", "testuser"));
	}

	@Given("^mock currency data provider$")
	public void mock_currency_data_provider() throws Throwable {
		openExchangeRateService = mock(OpenExchangeRateService.class);
		currencyService.setOpenExchangeRateService(openExchangeRateService);
	}

	@Given("^Open ExchangeRate currency data provider$")
	public void open_exchangerate_currency_data_provider() throws Throwable {
		currencyService.setOpenExchangeRateService(openExchangeRateService);
	}

	@Given("^from currency (\\w+)$")
	public void from_currency(String currencyCode) throws Throwable {
		from = currencyCode;
	}

	@Given("^to currency (\\w+)$")
	public void to_currency(String currencyCode) throws Throwable {
		to = currencyCode;
	}

	@When("^I ask for current exchange rate$")
	public void i_ask_for_current_exchange_rate() throws Throwable {
		result = currencyService.getExchangeRate(to,null);
	}

	@Then("^I should get ([\\d\\.]+)$")
	public void i_should_get(BigDecimal rate) throws Throwable {
		assertThat(this.result, is(rate));
	}

	@Then("^I should get no result$")
	public void i_should_get_no_result() throws Throwable {
		assertThat(this.result, nullValue());
	}

	@Then("^I should get a reasonable result$")
	public void i_should_get_reasonable() throws Throwable {
		assertThat(this.result, greaterThan(new BigDecimal(0.5)));
		assertThat(this.result, lessThan(new BigDecimal(1.5)));
	}

	@When("^I ask for exchange rate on (.*)$")
	public void i_ask_for_exchange_rate_on(@Format("yyyy-MM-dd") Date date) throws Throwable {
		result = currencyService.getExchangeRate(to, date);
	}

	@When("^I ask (\\d+) times for current exchange rate$")
	public void i_ask_times_for_current_exchange_rate(int count) throws Throwable {
		this.count = count;
		for (int i = 0; i < count; i++) {
			currencyService.getExchangeRate(to,null);
		}
	}
	
	@Given("^exchange rate (.*)$")
	public void exchange_rate(BigDecimal value) throws Throwable {
		when(currencyService.getExchangeRate("EUR",null)).thenReturn(value);
	}

}
