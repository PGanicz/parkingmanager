package com.example.demox.domain.model.payment;

import com.example.demox.domain.model.stepover.StopoverId;

import java.math.BigDecimal;
import java.util.Date;

public class Fee {
    private StopoverId stopoverId;
    private Date date;
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
