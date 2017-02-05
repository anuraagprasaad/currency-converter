package com.currency.conversion.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.currency.conversion.model.OpenExchangeData;
import com.currency.conversion.service.OpenExchangeRateService;

@Service
public class OpenExchangeRateServiceImpl implements OpenExchangeRateService {

	private static final Logger logger = LoggerFactory.getLogger(OpenExchangeRateServiceImpl.class);

	@Value("${openexchange.latestUrl}")
	private String latestUrl;

	@Value("${openexchange.historicalUrl}")
	private String historicalUrl;

	@Value("${openexchange.appId}")
	private String appId;
	
	@Override
	@Cacheable("latestRates")
	public BigDecimal getLatestExchangeRate(String currencyShortName) {
		logger.info("===getLatestExchangeRate=="+currencyShortName);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("appId", appId);
		
		OpenExchangeData openExchangeData = new RestTemplate().getForObject(latestUrl, OpenExchangeData.class,params);
        return openExchangeData.getRates().get(currencyShortName);
	}	
	
	@Override
	@Cacheable("historicalRates")
	public BigDecimal getHistoricalExchangeRate(String currencyShortName, Date timestamp) {
		logger.info("===getHistoricalExchangeRate=="+currencyShortName);
		Map<String, String> params = new HashMap<String, String>();
		params.put("appId", appId);
		if (timestamp != null) {
			params.put("date", new SimpleDateFormat("yyyy-MM-dd").format(timestamp));
		}
		OpenExchangeData openExchangeData = new RestTemplate().getForObject(latestUrl, OpenExchangeData.class,params);
        return openExchangeData.getRates().get(currencyShortName);
	}
}
