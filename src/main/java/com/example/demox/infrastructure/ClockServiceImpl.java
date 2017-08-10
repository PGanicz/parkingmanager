package com.example.demox.infrastructure;

import com.example.demox.domain.model.service.ClockService;

import java.util.Date;

public class ClockServiceImpl implements ClockService {
    @Override
    public Date getCurrentDate() {
        return new Date();
    }
}
