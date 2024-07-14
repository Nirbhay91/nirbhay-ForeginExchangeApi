package com.example.testing;

import com.example.ForeginExchangeApi.controller.*;
import com.example.ForeginExchangeApi.service.ExchangeRateService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

public class ExchangeRateControllerTest {
	
	   @InjectMocks
	    private ExchangeRateController controller;

	    @Mock
	    private ExchangeRateService service;
	    
	    @Test
	    public void testGetExchangeRate() {
	        String targetCurrency = "EUR";
	        controller.getExchangeRate(targetCurrency);
	        verify(service, times(1)).getExchangeRate(targetCurrency);
	    }

	    @Test
	    public void testGetLatestExchangeRates() {
	        String targetCurrency = "GBP";
	        controller.getLatestExchangeRates(targetCurrency);
	        verify(service, times(1)).getLatestExchangeRates(targetCurrency);
	    }
	    

}
