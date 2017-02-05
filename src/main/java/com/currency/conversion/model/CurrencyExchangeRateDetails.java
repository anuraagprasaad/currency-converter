package com.currency.conversion.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "CurrencyExchangeRateDetails")
@Table(name = "currency_exchange_rate_details")

public class CurrencyExchangeRateDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "from_currency")
	private Currencies fromCurrency;

	@Column(name = "to_currency")
	private Currencies toCurrency;

	@Column(name = "amount")
	private java.math.BigDecimal amount;

	@Column(name = "rate")
	private java.math.BigDecimal rate;

	@Column(name = "exchange_rate_conversion_date")
	private Date exchangeRateConversionDate;

	@Column(name = "historical_exchange_rate_date")
	private Date historicalExchangeRateDate;

	@Transient
	private String historicalExchangeRateDateStr;

	private String email;
	
	private java.math.BigDecimal total;

	public CurrencyExchangeRateDetails() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public java.math.BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(java.math.BigDecimal amount) {
		this.amount = amount;
	}

	public java.math.BigDecimal getRate() {
		return rate;
	}

	public void setRate(java.math.BigDecimal rate) {
		this.rate = rate;
	}

	public Date getExchangeRateConversionDate() {
		return exchangeRateConversionDate;
	}

	public void setExchangRateConversionDate(Date exchangeRateConversionDate) {
		this.exchangeRateConversionDate = exchangeRateConversionDate;
	}

	public Date getHistoricalExchangeRateDate() {
		return historicalExchangeRateDate;
	}

	public void setHistoricalExchangeRateDate(Date historicalExchangeRateDate) {
		this.historicalExchangeRateDate = historicalExchangeRateDate;
	}

	public String getHistoricalExchangeRateDateStr() {
		return historicalExchangeRateDateStr;
	}

	public void setHistoricalExchangeRateDateStr(String historicalExchangeRateDateStr) {
		this.historicalExchangeRateDateStr = historicalExchangeRateDateStr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public java.math.BigDecimal getTotal() {
		return total;
	}

	public void setTotal(java.math.BigDecimal total) {
		this.total = total;
	}

	public void setExchangeRateConversionDate(Date exchangeRateConversionDate) {
		this.exchangeRateConversionDate = exchangeRateConversionDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurrencyExchangeRateDetails other = (CurrencyExchangeRateDetails) obj;
		return Objects.equals(this.id, other.id);
	}

}
