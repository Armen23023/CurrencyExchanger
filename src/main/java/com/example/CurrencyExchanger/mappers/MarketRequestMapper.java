package com.example.CurrencyExchanger.mappers;

import com.example.CurrencyExchanger.dto.request.MarketRequest;
import com.example.CurrencyExchanger.model.Market;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class MarketRequestMapper implements Function<MarketRequest,Market> {

    @Override
    public Market apply(MarketRequest request) {
            Market market = new Market();
            market.setName(request.getName());
            return market;
    }
}
