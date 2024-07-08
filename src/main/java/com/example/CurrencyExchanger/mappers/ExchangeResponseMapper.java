package com.example.CurrencyExchanger.mappers;

import com.example.CurrencyExchanger.dto.response.ExchangeResponse;
import com.example.CurrencyExchanger.model.ExchangeHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ExchangeResponseMapper implements Function<ExchangeHistory, ExchangeResponse> {


    @Override
    public ExchangeResponse apply(ExchangeHistory exchangeHistory) {
        ExchangeResponse response = new ExchangeResponse();
        response.setId(exchangeHistory.getId());
        response.setFromMarket(exchangeHistory.getFromMarket().getName());
        response.setFromCurrency(exchangeHistory.getFromCurrency());
        response.setFromAmount(exchangeHistory.getFromAmount());

        response.setToMarket(exchangeHistory.getToMarket().getName());
        response.setToCurrency(exchangeHistory.getToCurrency());
        response.setToAmount(exchangeHistory.getToAmount());

        response.setTimestamp(exchangeHistory.getTimestamp());

        return response;
    }
}
