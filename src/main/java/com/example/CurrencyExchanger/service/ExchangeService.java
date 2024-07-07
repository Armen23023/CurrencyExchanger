package com.example.CurrencyExchanger.service;

import com.example.CurrencyExchanger.dto.request.ExchangeRequest;
import com.example.CurrencyExchanger.exeptions.BadRequestException;
import com.example.CurrencyExchanger.model.CurrencyCode;
import com.example.CurrencyExchanger.model.CurrencyRate;
import com.example.CurrencyExchanger.model.ExchangeHistory;
import com.example.CurrencyExchanger.model.Market;
import com.example.CurrencyExchanger.repository.CurrencyRateRepository;
import com.example.CurrencyExchanger.repository.ExchangeHistoryRepository;
import com.example.CurrencyExchanger.repository.MarketRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Log4j
public class ExchangeService {


    private final MarketRepository marketRepository;
    private final CurrencyRateRepository currencyRateRepository;

    private final ExchangeHistoryRepository exchangeHistoryRepository;

    public ExchangeService(MarketRepository marketRepository, CurrencyRateRepository currencyRateRepository, ExchangeHistoryRepository exchangeHistoryRepository) {
        this.marketRepository = marketRepository;
        this.currencyRateRepository = currencyRateRepository;
        this.exchangeHistoryRepository = exchangeHistoryRepository;
    }


    @Transactional
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




    @Transactional
    public ResponseEntity<String> exchangeMoney(ExchangeRequest exchangeRequest) {
        Market fromMarket = marketRepository.findByName(exchangeRequest.getFromMarket())
                .orElseThrow(() -> new BadRequestException("Market " + exchangeRequest.getFromMarket() + " not found"));

        CurrencyRate currencyFromRate = currencyRateRepository
                .findByCurrencyAndMarket(CurrencyCode.valueOf(exchangeRequest.getFromCurrency()),marketRepository.findByName(exchangeRequest.getFromMarket()).get());

        Market toMarket = marketRepository.findByName(exchangeRequest.getToMarket())
                .orElseThrow(() -> new BadRequestException("Market " + exchangeRequest.getToMarket() + " not found"));

        CurrencyRate currencyToRate = currencyRateRepository
                .findByCurrencyAndMarket(CurrencyCode.valueOf(exchangeRequest.getToCurrency()),marketRepository.findByName(exchangeRequest.getToMarket()).get());


        BigDecimal fromAmount = exchangeRequest.getFromAmount();
        BigDecimal toAmount;
            if (exchangeRequest.getFromCurrency().equals(exchangeRequest.getToCurrency()) || fromAmount.compareTo(BigDecimal.ZERO) <= 0){
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Impossible command change currencies or amount is equal or less than 0");
            }

           toAmount = multiply(fromAmount,currencyFromRate.getRateToAmd());
           toAmount = divide(toAmount,currencyToRate.getRateToAmd());


        ExchangeHistory history = ExchangeHistory.builder()
                .fromMarket(fromMarket)
                .toMarket(toMarket)
                .fromCurrency(currencyFromRate.getCurrency())
                .toCurrency(currencyToRate.getCurrency())
                .fromAmount(exchangeRequest.getFromAmount())
                .toAmount(toAmount)
                .timestamp(LocalDateTime.now())
                .build();
        //save History
        exchangeHistoryRepository.save(history);
        log.info("Transaction was complete");
        return ResponseEntity.status(HttpStatus.OK).body("The transaction was completed successfully");
    }


    private BigDecimal multiply(BigDecimal fromAmount,BigDecimal rate){
        return fromAmount.multiply(rate);
    }

    private BigDecimal divide(BigDecimal fromAmount,BigDecimal rete){
        return fromAmount.divide(rete,2,RoundingMode.HALF_DOWN);
    }
}
