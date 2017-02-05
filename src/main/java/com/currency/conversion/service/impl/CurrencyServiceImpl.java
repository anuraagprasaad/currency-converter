package com.currency.conversion.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.currency.conversion.dao.UserHistoryRepository;
import com.currency.conversion.form.CurrencyForm;
import com.currency.conversion.model.Currencies;
import com.currency.conversion.model.CurrencyExchangeRateDetails;
import com.currency.conversion.service.CurrencyService;
import com.currency.conversion.service.OpenExchangeRateService;
import com.currency.conversion.util.CurrencyConversionUtils;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	private static final Logger logger = LoggerFactory.getLogger(CurrencyServiceImpl.class);

	
	@Autowired
	private UserHistoryRepository historyRepository;
	
	@Autowired
	private OpenExchangeRateService openExchangeRateService;
	
	public static final int DEFAULT_SCALE = 6;

	public static final MathContext DEFAULT_MATH_CONTEXT = new MathContext(DEFAULT_SCALE, RoundingMode.HALF_EVEN);
	
	public CurrencyServiceImpl() {
		
	}
	
	private BigDecimal calculateRate(BigDecimal fromCurrencyRate, BigDecimal toCurrencyRate) {
		return ((new BigDecimal(1).divide(fromCurrencyRate,DEFAULT_MATH_CONTEXT)).multiply(toCurrencyRate,DEFAULT_MATH_CONTEXT));
	}
	
	private BigDecimal calculateTotalRate(BigDecimal fromCurrencyRate, BigDecimal toCurrencyRate, BigDecimal amount) {
		return ((new BigDecimal(1).divide(fromCurrencyRate,DEFAULT_MATH_CONTEXT)).multiply(toCurrencyRate,DEFAULT_MATH_CONTEXT)).multiply(amount, DEFAULT_MATH_CONTEXT);
	}
	
	@Override
	public BigDecimal getExchangeRate(String currencyCode, Date historicalDate) {
		BigDecimal rate = null;
		if(historicalDate != null)
			rate = openExchangeRateService.getHistoricalExchangeRate(currencyCode, historicalDate);
		else
			rate = openExchangeRateService.getLatestExchangeRate(currencyCode);
		
		return rate;
	}
	
		
	@Override
	public CurrencyForm convert(BigDecimal amount, String fromCurrencyCode, String toCurrencyCode,Date historicalDate) {
		BigDecimal fromCurrencyRate = getExchangeRate(fromCurrencyCode,historicalDate);
		BigDecimal toCurrencyRate = getExchangeRate(toCurrencyCode,historicalDate);
				
		if(amount == null)
			amount = new BigDecimal("1");		
		
		logger.info("==fromCurrencyCode=="+fromCurrencyCode+"==toCurrencyCode=="+toCurrencyCode+"==fromCurrencyRate=="+fromCurrencyRate+"==toCurrencyRate=="+toCurrencyRate+"==amount=="+amount+"==historicalDate=="+historicalDate);
		
		return save(fromCurrencyCode,toCurrencyCode,historicalDate,calculateRate(fromCurrencyRate, toCurrencyRate),amount,calculateTotalRate(fromCurrencyRate, toCurrencyRate, amount));
	}

	protected CurrencyForm save(String fromCurrency, String toCurrency, Date historicalExchangeRateDate, BigDecimal rate, BigDecimal amount, BigDecimal total) {

		CurrencyExchangeRateDetails currencyDetails = new CurrencyExchangeRateDetails();
		currencyDetails.setEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		currencyDetails.setFromCurrency(Currencies.valueOf(fromCurrency));
		currencyDetails.setToCurrency(Currencies.valueOf(toCurrency));
		currencyDetails.setExchangRateConversionDate(new Date());
		currencyDetails.setHistoricalExchangeRateDate(historicalExchangeRateDate);
		currencyDetails.setRate(rate);
		currencyDetails.setAmount(amount);
		currencyDetails.setTotal(total);
		historyRepository.save(currencyDetails);
		
		return new CurrencyConversionUtils().convertCurrencyObjectToCurrencyForm(currencyDetails);
	}

	@Override
	public void setOpenExchangeRateService(OpenExchangeRateService openExchangeRateService) {
		this.openExchangeRateService = openExchangeRateService;
	}
	
	
}
