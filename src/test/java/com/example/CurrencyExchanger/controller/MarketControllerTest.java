package com.example.CurrencyExchanger.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.CurrencyExchanger.dto.request.MarketRequest;
import com.example.CurrencyExchanger.service.MarketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class MarketControllerTest {

    @Mock
    private  MarketService marketService;

    @InjectMocks
    private MarketController marketController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(marketController).build();
        objectMapper = new ObjectMapper();
    }
// TODO createMarket test is wrong
    @Test
    void createMarket() throws Exception {
        MarketRequest market = new MarketRequest("Ararat");

        String marketJson = objectMapper.writeValueAsString(market);
        mockMvc.perform(post("/api/markets")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(marketJson))
                .andExpect(status().isCreated());
        verify(marketService,times(1)).createMarket(market);
    }

    @Test
    void getMarketByName() throws Exception {
        mockMvc.perform(get("/api/markets?marketName=Ararat Bank")).andExpect(status().isOk());
    }

    @Test
    void deleteByName() throws Exception {
        mockMvc.perform(delete("/api/markets/Ararat Bank")).andExpect(status().isOk());

    }
}