package com.example.CurrencyExchanger.controller;


import com.example.CurrencyExchanger.dto.request.ExchangeRequest;
import com.example.CurrencyExchanger.service.ExchangeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ExchangeControllerTest {

    @InjectMocks
    private ExchangeController exchangeController;

    @Mock
    private ExchangeService exchangeService;

    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(exchangeController).build();

    }
//TODO: setCurrencyRate return actual 200 expected 201
    @Test
    void setCurrencyRate() throws Exception {
        mockMvc.perform(post("/api-exchange/set-currency-rate?currency=AMD&rateToAMD=1&marketName=ACBA")).andExpect(status().isOk());
    }
//TODO RequestBody
    @Test
    void exchangeMoney() throws Exception {
        ExchangeRequest request = new ExchangeRequest();
        mockMvc.perform(post("/api-exchange/exchange-money")).andExpect(status().isOk());
    }

    @Test
    void getExchangeHistoryById() throws Exception {
        mockMvc.perform(get("/api-exchange/history/1")).andExpect(status().isOk());
    }

    @Test
    void allExchangeHistory() throws Exception {
        mockMvc.perform(get("/api-exchange/history")).andExpect(status().isOk());
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/api-exchange/history/1")).andExpect(status().isOk());
    }
}