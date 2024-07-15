package com.example.CurrencyExchanger.service;



import com.example.CurrencyExchanger.dto.request.MarketRequest;
import com.example.CurrencyExchanger.dto.response.MarketResponse;
import com.example.CurrencyExchanger.exeptions.BadRequestException;
import com.example.CurrencyExchanger.mappers.MarketRequestMapper;
import com.example.CurrencyExchanger.mappers.MarketResponseMapper;

import com.example.CurrencyExchanger.model.Market;
import com.example.CurrencyExchanger.repository.MarketRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MarketServiceTest {



    @Mock
    private  MarketRepository marketRepository;

    @Mock
    private  MarketResponseMapper responseMapper;

    @InjectMocks
    private MarketService marketService ;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void createMarket() {
        //given
        MarketRequest request = new MarketRequest("ACBA");
        Market market = new Market(1L,"ACBA",null);
        Market savedMarket = new Market(1L,"ACBA",null);

        //When
        ResponseEntity<String> responseEntity = marketService.createMarket(request);

        //Then

        Assertions.assertEquals("Market created successfully.",responseEntity.getBody());

    }

    @Test
    void createMarketAlreadyExists() {
        MarketRequest request = new MarketRequest("ACBA");
        Market market = new Market(1L,"ACBA",null);

        //Mock
//        when(requestMapper.apply(request)).thenReturn(market);
//        when(marketRepository.save(market)).thenReturn(savedMarket);
//        when(responseMapper.apply(savedMarket)).thenReturn(new ResponseEntity<String>(HttpStatus.OK));

        //When
        ResponseEntity<String> responseEntity = marketService.createMarket(request);


        //Then

        Assertions.assertEquals("Market with this name already exists.",responseEntity.getBody());

    }

    @Test
    void getMarketByName() {

        //Given
        String name = "ACBA";
        Market market = new Market(1L,"ACBA",null);


        //Mock
        when(marketRepository.existsByName(name)).thenReturn(true);
        when(marketRepository.findByName(name)).thenReturn(Optional.of(market));
        when(responseMapper.apply(any(Market.class))).thenReturn(new MarketResponse(1L, "ACBA", null));


        //When
        MarketResponse marketByName = marketService.getMarketByName(name);

        //Then
        Assertions.assertEquals(marketByName.getName(),market.getName());

        verify(marketRepository, times(1)).existsByName(name);
        verify(marketRepository, times(1)).findByName(name);

    }

    @Test
    void getMarketByName_MarketNotFound() {
        // Given
        String name = "Nonexistent Market";

        // Mock
        when(marketRepository.existsByName(name)).thenReturn(false);

        // Then
        BadRequestException exception = Assertions.assertThrows(
                BadRequestException.class,
                () -> marketService.getMarketByName(name)
        );

        Assertions.assertEquals("Market with this name is not found", exception.getMessage());
        verify(marketRepository, times(1)).existsByName(name);
        verify(marketRepository, times(0)).findByName(name);
    }

    @Test
    void deleteByName() {

    }
}