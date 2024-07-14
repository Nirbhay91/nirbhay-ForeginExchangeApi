package com.example.ForeginExchangeApi.controller;

import com.example.ForeginExchangeApi.modal.ExchangeRate;
import com.example.ForeginExchangeApi.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fx")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService service;

    @GetMapping
    public ExchangeRate getExchangeRate(@RequestParam String targetCurrency) {
        return service.getExchangeRate(targetCurrency);
    }

    @GetMapping("/{targetCurrency}")
    public List<ExchangeRate> getLatestExchangeRates(@PathVariable String targetCurrency) {
        return service.getLatestExchangeRates(targetCurrency);
    }
}
