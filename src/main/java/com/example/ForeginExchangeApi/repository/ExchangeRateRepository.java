package com.example.ForeginExchangeApi.repository;


import com.example.ForeginExchangeApi.modal.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    List<ExchangeRate> findByTargetCurrencyOrderByDateDesc(String targetCurrency);
    ExchangeRate findTopByDateAndTargetCurrency(LocalDate date, String targetCurrency);
}
