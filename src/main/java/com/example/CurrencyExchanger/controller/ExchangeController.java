package com.example.CurrencyExchanger.controller;

import com.example.CurrencyExchanger.dto.request.ExchangeRequest;
import com.example.CurrencyExchanger.dto.response.ExchangeListResponse;
import com.example.CurrencyExchanger.dto.response.ExchangeResponse;
import com.example.CurrencyExchanger.model.CurrencyCode;
import com.example.CurrencyExchanger.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api-exchange")
public class ExchangeController {


   private final ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @PostMapping("/set-currency-rate")
    public ResponseEntity<String> setCurrencyRate(@RequestParam CurrencyCode currency,
                                                  @RequestParam BigDecimal rateToAMD,
                                                  @RequestParam String marketName) {
         return  exchangeService.setCurrencyRate(currency,rateToAMD,marketName);
    }


    @PostMapping("/exchange-money")
    public ResponseEntity<String> exchangeMoney(@RequestBody ExchangeRequest exchangeRequest){
        return exchangeService.exchangeMoney(exchangeRequest);
    }

    @GetMapping("history/{id}")
    public ResponseEntity<ExchangeResponse> getExchangeHistoryById(@PathVariable(name = "id") final long id ){
        return ResponseEntity.ok(exchangeService.getHistoryById(id));
    }

    @GetMapping("/history")
    public ResponseEntity<ExchangeListResponse> allExchangeHistory(@RequestParam(name = "page", required = false, defaultValue = "0") final int page,
                                                                   @RequestParam(name = "size", required = false, defaultValue = "10") final int size){
        return ResponseEntity.ok(exchangeService.allHistory(page,size));
    }

    @DeleteMapping("/history/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") final long id ){
        exchangeService.deleteById(id);
        return ResponseEntity.ok("History was removed");
    }




}