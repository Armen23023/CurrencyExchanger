package com.example.CurrencyExchanger.exeptions;

public class BadRequestException  extends RuntimeException {

    private final String message;

    public BadRequestException(final String message) {
        super(message);
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
