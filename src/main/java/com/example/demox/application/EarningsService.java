package com.example.demox.application;

import com.example.demox.domain.model.payment.Fee;

import java.util.Date;

public interface EarningsService {
    Fee getTotalEarningsForDay(Date date);
}
