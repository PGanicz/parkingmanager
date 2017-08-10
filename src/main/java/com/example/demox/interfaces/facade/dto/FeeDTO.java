package com.example.demox.interfaces.facade.dto;

public class FeeDTO {
    private final String value;
    private final String currency;

    public FeeDTO(String value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public String getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "FeeDTO{" +
                "value='" + value + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
