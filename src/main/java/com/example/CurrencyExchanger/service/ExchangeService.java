package com.example.CurrencyExchanger.service;

import com.example.CurrencyExchanger.model.CurrencyCode;
import com.example.CurrencyExchanger.model.CurrencyRate;
import com.example.CurrencyExchanger.model.Market;
import com.example.CurrencyExchanger.repository.CurrencyRateRepository;
import com.example.CurrencyExchanger.repository.MarketRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Log4j
public class ExchangeService {


    private final MarketRepository marketRepository;
    private final CurrencyRateRepository currencyRateRepository;

    public ExchangeService(MarketRepository marketRepository, CurrencyRateRepository currencyRateRepository) {
        this.marketRepository = marketRepository;
        this.currencyRateRepository = currencyRateRepository;
    }


    public ResponseEntity<String> setCurrencyRate(CurrencyCode currency, BigDecimal rateToAMD, String marketName) {

        Optional<Market> market = marketRepository.findByName(marketName);

        if (market.isEmpty()) {
            log.info("Market with this name : "+ marketName +" not found");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Market with this name : "+ marketName +" not found");
        }


        CurrencyRate currencyRate = currencyRateRepository.findByCurrencyAndMarket(currency, market.get());
        if (currencyRate == null) {
            currencyRate = new CurrencyRate();
            currencyRate.setCurrency(currency);
            currencyRate.setMarket(market.get());
        }

        currencyRate.setRateToAmd(rateToAMD);
        currencyRateRepository.save(currencyRate);

        return ResponseEntity.status(HttpStatus.OK).body("Currency rate updated successfully.");
    }
}
