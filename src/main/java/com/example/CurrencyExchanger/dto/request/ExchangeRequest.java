package com.example.CurrencyExchanger.dto.request;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ExchangeRequest {
    private String fromMarket;
    private String toMarket;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal fromAmount;
}
