package com.example.demox.domain.model.fee;

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

    public Fee(BigDecimal fine, String currency, Date paymentTime) {
        this.paymentTime = paymentTime;
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
        return fine;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fee fee = (Fee) o;

        if (paymentTime != null ? !paymentTime.equals(fee.paymentTime) : fee.paymentTime != null) return false;
        if (fine != null ? !fine.equals(fee.fine) : fee.fine != null) return false;
        return currency != null ? currency.equals(fee.currency) : fee.currency == null;
    }

    @Override
    public int hashCode() {
        int result = paymentTime != null ? paymentTime.hashCode() : 0;
        result = 31 * result + (fine != null ? fine.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }
}
