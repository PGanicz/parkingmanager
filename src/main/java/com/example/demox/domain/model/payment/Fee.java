package com.example.demox.domain.model.payment;

import java.math.BigDecimal;
import java.util.Date;

public class Fee {
    private Date paymentTime;
    private BigDecimal fine;
    private String currency;

    public Fee(BigDecimal fine, String currency) {
        this.fine = fine;
        this.currency = currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public BigDecimal getFine() {
        return new BigDecimal(fine.byteValueExact());
    }

    public String getCurrency() {
        return currency;
    }
}
