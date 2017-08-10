package com.example.demox.application.impl;

import com.example.demox.application.ParkingCheckerService;
import com.example.demox.domain.model.stepover.NumberPlate;
import com.example.demox.domain.model.stepover.StopoverRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ParkingCheckerServiceImpl implements ParkingCheckerService {

    private StopoverRepository stopoverRepository;

    @Autowired
    public void setStopoverRepository(StopoverRepository stopoverRepository) {
        this.stopoverRepository = stopoverRepository;
    }

    @Override
    public boolean check(NumberPlate numberPlate) {
        return false;
    }
}
