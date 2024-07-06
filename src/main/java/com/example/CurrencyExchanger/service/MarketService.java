package com.example.CurrencyExchanger.service;

import com.example.CurrencyExchanger.dto.request.MarketRequest;
import com.example.CurrencyExchanger.mappers.MarketRequestMapper;
import com.example.CurrencyExchanger.model.Market;
import com.example.CurrencyExchanger.repository.MarketRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j
public class MarketService {

    private final MarketRepository marketRepository;

    private final MarketRequestMapper requestMapper;



    public MarketService(MarketRepository marketRepository, MarketRequestMapper requestMapper) {
        this.marketRepository = marketRepository;
        this.requestMapper = requestMapper;

    }


    @Transactional
    public ResponseEntity<String> createMarket(MarketRequest marketRequest) {
        // Check if market with the same name already exists
        if (marketRepository.existsByName(marketRequest.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Market with this name already exists.");
        }

        // Create a new market entity
        Market market = marketRepository.save(requestMapper.apply(marketRequest));
        log.info("Market :,"+ market + " successfully added" );


        return ResponseEntity.status(HttpStatus.CREATED).body("Market created successfully.");

    }
}
