package com.example.demox.domain.model;

import java.util.Date;

public interface FeeCalculationStrategy {

    Fee calculateFee(Long hours);
}
