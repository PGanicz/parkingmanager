package com.example.demox.interfaces.facade.dto;

import com.example.demox.domain.model.fee.Fee;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class FeeDTO {
    private final BigDecimal value;
    private final String currency;

    public FeeDTO(BigDecimal value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public FeeDTO(Fee fee) {
        this.value = fee.getFine().round(new MathContext(3 , RoundingMode.HALF_UP));
        this.currency = fee.getCurrency();
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }
}
