package com.currency.conversion.service;

import java.math.BigDecimal;
import java.util.Date;

public interface OpenExchangeRateService {
	/*public BigDecimal getHistoricalExchangeRate(String baseCurrencyShortName, String toCurrencyShortName, Date timestamp);
	public BigDecimal getLatestExchangeRate(String baseCurrencyShortName, String toCurrencyShortName);	*/
	public BigDecimal getHistoricalExchangeRate(String currencyShortName, Date timestamp);
	public BigDecimal getLatestExchangeRate(String currencyShortName);	
}
