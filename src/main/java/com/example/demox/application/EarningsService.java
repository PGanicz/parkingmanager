package com.example.demox.application;

import com.example.demox.domain.model.Fee;

import java.util.Date;

public interface EarningsService {
    Fee getTotalEarningsForDay(Date date);
}
