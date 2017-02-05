package com.currency.conversion.service;

import java.math.BigDecimal;
import java.util.Date;

import com.currency.conversion.exception.OpenExchangeRateException;
import com.currency.conversion.form.CurrencyForm;

public interface CurrencyService {
	public CurrencyForm convert(BigDecimal amount, String fromCurrencycode, String toCurrencyCode,Date historicalDate) throws OpenExchangeRateException;
	public void setOpenExchangeRateService(OpenExchangeRateService openExchangeRateService);
	public BigDecimal getExchangeRate(String currencyCode, Date historicalDate);
}
