package com.example.demox.domain.model.payment;

public interface FeeCalculationStrategy {

    Fee calculateFee(Long hours);
}
