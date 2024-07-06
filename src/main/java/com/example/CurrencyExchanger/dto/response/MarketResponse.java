package com.example.CurrencyExchanger.dto.response;

import com.example.CurrencyExchanger.model.CurrencyRate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarketResponse {
    private Long id;
    private String name;
    private Set<CurrencyRate> currencyRates;

}
