package com.example.CurrencyExchanger.dto.request;

import com.example.CurrencyExchanger.model.CurrencyCode;
import com.example.CurrencyExchanger.model.Market;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ExchangeRequest {
    private String fromMarket;
    private String toMarket;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal fromAmount;
}
