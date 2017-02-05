package com.currency.converter.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.currency.conversion.model.CurrencyExchangeRateDetails;

public interface UserHistoryRepository extends JpaRepository<CurrencyExchangeRateDetails, Long> {
	List<CurrencyExchangeRateDetails> findTop10ByEmailOrderByExchangeRateConversionDateDesc(String email);

}
