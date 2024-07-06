package com.example.CurrencyExchanger.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CurrencyCode currency;

    private BigDecimal rateToAmd;

    @ManyToOne
    @JoinColumn(name = "market_id")
    private Market market;


}