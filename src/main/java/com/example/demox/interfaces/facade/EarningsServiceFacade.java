package com.example.demox.interfaces.facade;

import com.example.demox.interfaces.facade.dto.FeeDTO;

import java.util.Date;

public interface EarningsServiceFacade {
    FeeDTO getTotalEarningsForDay(Date date);
}
