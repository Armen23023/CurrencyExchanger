package com.example.CurrencyExchanger.controller;

import com.example.CurrencyExchanger.dto.request.MarketRequest;
import com.example.CurrencyExchanger.dto.response.MarketResponse;
import com.example.CurrencyExchanger.service.MarketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/markets")
public class MarketController {

    private final MarketService  marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @PostMapping
    public ResponseEntity<String> createMarket(@RequestBody MarketRequest marketRequest) {
       return marketService.createMarket(marketRequest);
    }

    @GetMapping
    public  ResponseEntity<MarketResponse> getMarketByName(@RequestParam String  marketName){
        return ResponseEntity.ok(marketService.getMarketByName(marketName));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteByName(@PathVariable(name = "name") final String name){
        marketService.deleteByName(name);
        return ResponseEntity.ok("Market was removed");
    }
}
