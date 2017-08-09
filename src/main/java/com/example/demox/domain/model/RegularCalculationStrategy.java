package com.example.demox.domain.model;

import java.math.BigDecimal;

public class RegularCalculationStrategy implements FeeCalculationStrategy {


    /*
    * This function calculates: a (1-q^n)/(1-q)
    *
    * */
    @Override
    public Fee calculateFee(Long hours) {
        BigDecimal qFactor = new BigDecimal(2);
        BigDecimal nthValue = qFactor.pow((int)(hours - 1));
        BigDecimal one = new BigDecimal(1);

        BigDecimal geometricSum = (one.subtract(nthValue)).divide(one.subtract(qFactor));
        BigDecimal result = geometricSum.add(one);

        return new Fee(result, "PLN");
    }
}
