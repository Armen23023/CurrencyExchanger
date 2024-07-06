package com.example.CurrencyExchanger.controller;

import com.example.CurrencyExchanger.model.CurrencyCode;
import com.example.CurrencyExchanger.model.CurrencyRate;
import com.example.CurrencyExchanger.model.Market;
import com.example.CurrencyExchanger.repository.CurrencyRateRepository;
import com.example.CurrencyExchanger.repository.MarketRepository;
import com.example.CurrencyExchanger.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api-exchange")
public class ExchangeController {

    @Autowired
    ExchangeService exchangeService;

    @PostMapping("/set-currency-rate")
    public ResponseEntity<String> setCurrencyRate(@RequestParam CurrencyCode currency,
                                                  @RequestParam BigDecimal rateToAMD,
                                                  @RequestParam String marketName) {
         return  exchangeService.setCurrencyRate(currency,rateToAMD,marketName);
    }
}