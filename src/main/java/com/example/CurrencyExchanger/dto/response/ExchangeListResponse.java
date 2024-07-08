package com.example.CurrencyExchanger.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ExchangeListResponse {

    private List<ExchangeResponse> list;
    private int totalPage;
}
