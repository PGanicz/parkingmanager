package com.example.parkingmanager.interfaces.facade;

import com.example.parkingmanager.interfaces.facade.dto.FeeDTO;

import java.text.ParseException;

public interface EarningsServiceFacade {
    FeeDTO getTotalEarningsForDay(String date) throws ParseException;
}
