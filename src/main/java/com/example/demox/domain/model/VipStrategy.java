package com.example.demox.domain.model;

import java.math.BigDecimal;


public class VipStrategy implements FeeStrategy {
    @Override
    public Fee calculateFee(Long hours) {
        BigDecimal qFactor = new BigDecimal(1.5);
        BigDecimal nthValue = qFactor.pow((int)(hours - 1));
        BigDecimal one = new BigDecimal(1);

        BigDecimal geometricSum = (one.subtract(nthValue)).divide(one.subtract(qFactor));

        return new Fee(geometricSum, "PLN");
    }
}
