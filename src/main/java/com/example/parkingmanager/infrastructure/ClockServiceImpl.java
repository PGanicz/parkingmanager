package com.example.parkingmanager.infrastructure;

import com.example.parkingmanager.domain.model.clock.ClockService;

import java.util.Date;

public class ClockServiceImpl implements ClockService {
    @Override
    public Date getCurrentDate() {
        return new Date();
    }
}
