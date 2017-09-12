package com.example.parkingmanager.application;

import com.example.parkingmanager.domain.model.fee.Fee;

import java.util.Date;

public interface EarningsService {
    Fee getTotalEarningsForDay(Date date);
}
