package com.example.ForeginExchangeApi.service;

import com.example.ForeginExchangeApi.modal.ExchangeRate;
import com.example.ForeginExchangeApi.modal.ExchangeRateResponse;
import com.example.ForeginExchangeApi.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ExchangeRateService {
	
	private static final Logger logger = Logger.getLogger(ExchangeRateService.class.getName());
	
    @Autowired
    private ExchangeRateRepository repository;

    private final RestTemplate restTemplate = new RestTemplate();

    public ExchangeRate getExchangeRate(String targetCurrency) {
        LocalDate today = LocalDate.now();
        ExchangeRate rate = repository.findTopByDateAndTargetCurrency(today, targetCurrency);
        if (rate == null) {
            logger.info("No data found in database, fetching from external API...");
            rate = fetchAndSaveExchangeRate(targetCurrency);
        } else {
            logger.info("Data found in database.");
        }
        return rate;
    }

    public List<ExchangeRate> getLatestExchangeRates(String targetCurrency) {
    	 List<ExchangeRate> rates = repository.findByTargetCurrencyOrderByDateDesc(targetCurrency);
         logger.info("Fetched Rates: " + rates);
         return rates;
    }

    private ExchangeRate fetchAndSaveExchangeRate(String targetCurrency) {
        String url = "https://api.frankfurter.app/latest?to=" + targetCurrency;
        ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);
        if (response != null && response.getRates() != null) {
            ExchangeRate rate = new ExchangeRate();
            rate.setDate(response.getDate());
            rate.setSourceCurrency("USD"); //Default source currency is USD
            rate.setTargetCurrency(targetCurrency);
            rate.setRate(response.getRates().get(targetCurrency));
            repository.save(rate);
            logger.info("Fetched and saved rate from external API: " + rate);
            return rate;
        }
        logger.warning("Failed to fetch rate from external API.");
        return null;
    }
}
