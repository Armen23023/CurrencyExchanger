package com.example.CurrencyExchanger.dto.response;

import com.example.CurrencyExchanger.model.CurrencyCode;
import com.example.CurrencyExchanger.model.Market;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeResponse {
    private Long id;
    private String  fromMarket;
    private String toMarket;
    private CurrencyCode fromCurrency;
    private CurrencyCode toCurrency;
    private BigDecimal fromAmount;
    private BigDecimal toAmount;
    private LocalDateTime timestamp;
}
