package com.example.CurrencyExchanger.service;



import com.example.CurrencyExchanger.mappers.MarketRequestMapper;
import com.example.CurrencyExchanger.mappers.MarketResponseMapper;

import com.example.CurrencyExchanger.repository.MarketRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
class MarketServiceTest {

    @InjectMocks
    private MarketService marketService ;

    @Mock
    private  MarketRepository marketRepository;
    @Mock
    private  MarketRequestMapper requestMapper;
    @Mock
    private  MarketResponseMapper responseMapper;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void createMarket() {
    }

    @Test
    void createMarketAlreadyExists() {

    }

    @Test
    void getMarketByName() {

    }

    @Test
    void deleteByName() {
    }
}