package com.example.demox.domain.model;

import java.math.BigDecimal;

public class Fee {
    private BigDecimal fine;
    private String currency;

    public Fee(BigDecimal fine, String currency) {
        this.fine = fine;
        this.currency = currency;
    }

    public BigDecimal getFine() {
        return new BigDecimal(fine.byteValueExact());
    }

    public String getCurrency() {
        return currency;
    }
}
