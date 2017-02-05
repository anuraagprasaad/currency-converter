package com.currency.conversion.web;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.currency.conversion.exception.OpenExchangeRateException;
import com.currency.conversion.form.CurrencyForm;
import com.currency.conversion.model.Currencies;
import com.currency.conversion.service.CurrencyService;
import com.currency.conversion.service.UserService;

@Controller
public class CurrencyConversionController extends WebMvcConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);

	private CurrencyService currencyService;
	private UserService userService;

	@Autowired
	public CurrencyConversionController(CurrencyService currencyService, UserService userService) {
		this.currencyService = currencyService;
		this.userService = userService;
	}

	@ModelAttribute("currencies")
	protected Currencies[] getCurrencyValues() {
		return Currencies.values();
	}

	protected void loadHistory(Model model, Principal user) {
		model.addAttribute("history", this.userService.findTop10ByEmailOrderByIdDesc());
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model, Principal user) {
		model.addAttribute("currency", new CurrencyForm());
		loadHistory(model, user);
		return "home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String convert(@Valid @ModelAttribute("currency") CurrencyForm form, BindingResult bindingResult,
			Model model, Principal user) throws OpenExchangeRateException {

		if (!bindingResult.hasErrors()) {
			Date queryExchgRateDateYYYYMMDDFormat = null;
			if(form.getHistoricalExchangeRateDate() != null) {
				try {
					queryExchgRateDateYYYYMMDDFormat = new SimpleDateFormat("yyyy-MM-dd").parse(form.getHistoricalExchangeRateDate());
				} catch (ParseException e) {
					logger.error("Error converting date!");
				}
			}				
			model.addAttribute("currency", this.currencyService.convert(form.getAmount(), form.getFromCurrency().name(),
					form.getToCurrency().name(), queryExchgRateDateYYYYMMDDFormat));
		}
		loadHistory(model, user);
		return "home";
	}
}