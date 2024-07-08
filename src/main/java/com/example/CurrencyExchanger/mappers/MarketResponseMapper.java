package com.example.CurrencyExchanger.mappers;

import com.example.CurrencyExchanger.dto.response.MarketResponse;
import com.example.CurrencyExchanger.model.Market;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class MarketResponseMapper implements Function<Market, MarketResponse> {

    @Override
    public MarketResponse apply(Market response) {
        MarketResponse marketResponse = new MarketResponse();
        marketResponse.setName(response.getName());
        marketResponse.setCurrencyRates(response.getCurrencyRates());
        return marketResponse;
    }
}