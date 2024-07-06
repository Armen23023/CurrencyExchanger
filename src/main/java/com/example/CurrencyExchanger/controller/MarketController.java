package com.example.CurrencyExchanger.controller;

import com.example.CurrencyExchanger.dto.request.MarketRequest;
import com.example.CurrencyExchanger.service.MarketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/markets")
public class MarketController {

    private final MarketService  marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @PostMapping
    public ResponseEntity<ResponseEntity<String>> createMarket(@RequestBody MarketRequest marketRequest) {
       return ResponseEntity.ok(marketService.createMarket(marketRequest));
    }
}
