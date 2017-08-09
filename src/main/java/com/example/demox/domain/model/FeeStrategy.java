package com.example.demox.domain.model;

import java.util.Date;

public interface FeeStrategy {

    Fee calculateFee(Long hours);
}
