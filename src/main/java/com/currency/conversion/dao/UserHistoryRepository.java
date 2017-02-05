package com.currency.conversion.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.currency.conversion.model.CurrencyExchangeRateDetails;

public interface UserHistoryRepository extends JpaRepository<CurrencyExchangeRateDetails, Long> {
	List<CurrencyExchangeRateDetails> findTop10ByEmailOrderByIdDesc(String email);

}
