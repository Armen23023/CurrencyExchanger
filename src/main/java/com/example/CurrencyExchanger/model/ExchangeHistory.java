package com.example.CurrencyExchanger.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExchangeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "from_market_id")
    private Market fromMarket;

    @ManyToOne
    @JoinColumn(name = "to_market_id")
    private Market toMarket;

    @Enumerated(EnumType.STRING)
    private CurrencyCode fromCurrency;

    @Enumerated(EnumType.STRING)
    private CurrencyCode toCurrency;

    private BigDecimal fromAmount;
    private BigDecimal toAmount;
    private LocalDateTime timestamp;

}
