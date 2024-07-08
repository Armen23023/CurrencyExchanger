package com.example.CurrencyExchanger.exeptions;

import lombok.Getter;

@Getter
public class BadRequestException  extends RuntimeException {

    private final String message;

    public BadRequestException(final String message) {
        super(message);
        this.message = message;
    }


}
