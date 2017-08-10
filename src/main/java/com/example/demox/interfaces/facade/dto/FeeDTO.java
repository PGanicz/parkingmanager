package com.example.demox.interfaces.facade.dto;

import com.example.demox.domain.model.payment.Fee;

public class FeeDTO {
    private final long value;
    private final String currency;

    public FeeDTO(long value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public FeeDTO(Fee fee) {
        this.value = fee.getFine().longValue();
        this.currency = fee.getCurrency();
    }

    public long getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }
}
