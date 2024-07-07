package com.example.CurrencyExchanger.repository;

import com.example.CurrencyExchanger.model.CurrencyCode;
import com.example.CurrencyExchanger.model.CurrencyRate;
import com.example.CurrencyExchanger.model.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {
    CurrencyRate findByCurrencyAndMarket(CurrencyCode currency, Market market);


}