package com.currency.conversion.form;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.currency.conversion.model.Currencies;

public class CurrencyForm {
	
	
	@NotNull
	private Currencies fromCurrency;
	@NotNull
	private Currencies toCurrency;
	@NotNull
	private BigDecimal amount;
	private BigDecimal rate;	
	private String historicalExchangeRateDate;
	private String conversionDate;
	private BigDecimal total;
	private Date exchangeRateConversionDate;
		
	public Date getExchangeRateConversionDate() {
		return exchangeRateConversionDate;
	}

	public void setExchangeRateConversionDate(Date exchangeRateConversionDate) {
		this.exchangeRateConversionDate = exchangeRateConversionDate;
	}

	public Currencies getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(Currencies fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public Currencies getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(Currencies toCurrency) {
		this.toCurrency = toCurrency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getHistoricalExchangeRateDate() {
		return historicalExchangeRateDate;
	}

	public void setHistoricalExchangeRateDate(String historicalExchangeRateDate) {
		this.historicalExchangeRateDate = historicalExchangeRateDate;
	}
	

	public String getConversionDate() {
		return conversionDate;
	}

	public void setConversionDate(String conversionDate) {
		this.conversionDate = conversionDate;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
		
}
