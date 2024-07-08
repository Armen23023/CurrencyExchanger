package com.example.CurrencyExchanger.service;

import com.example.CurrencyExchanger.dto.request.MarketRequest;
import com.example.CurrencyExchanger.dto.response.MarketResponse;
import com.example.CurrencyExchanger.exeptions.BadRequestException;
import com.example.CurrencyExchanger.mappers.MarketRequestMapper;
import com.example.CurrencyExchanger.mappers.MarketResponseMapper;
import com.example.CurrencyExchanger.model.Market;
import com.example.CurrencyExchanger.repository.MarketRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j
public class MarketService {

    private final MarketRepository marketRepository;

    private final MarketRequestMapper requestMapper;

    private final MarketResponseMapper responseMapper;



    public MarketService(MarketRepository marketRepository, MarketRequestMapper requestMapper, MarketResponseMapper responseMapper) {
        this.marketRepository = marketRepository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }




    @Transactional
    public ResponseEntity<String> createMarket(MarketRequest marketRequest) {

        if (marketRepository.existsByName(marketRequest.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Market with this name already exists.");
        }

        Market market = marketRepository.save(requestMapper.apply(marketRequest));
        log.info("Market :,"+ market + " successfully added" );

        return ResponseEntity.status(HttpStatus.CREATED).body("Market created successfully.");

    }

    public MarketResponse getMarketByName(String marketName) {
        if (!marketRepository.existsByName(marketName)) {
            throw new BadRequestException("Market with this name is not found");
        }
        Optional<Market> market = marketRepository.findByName(marketName);

        return responseMapper.apply(market.get());

    }

    public void deleteByName(String name) {
        if (marketRepository.existsByName(name)){
            marketRepository.deleteByName(name);
        }else {
            throw new BadRequestException("Market not exists ");
        }
    }
}
