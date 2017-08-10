package com.example.demox.interfaces.facade.internal;

import com.example.demox.application.ParkingCheckerService;
import com.example.demox.domain.model.stepover.NumberPlate;
import com.example.demox.interfaces.facade.ParkingCheckerServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;

public class ParkingCheckerServiceFacadeImpl implements ParkingCheckerServiceFacade {
    private ParkingCheckerService parkingCheckerService;

    @Autowired
    public void setParkingCheckerService(ParkingCheckerService parkingCheckerService) {
        this.parkingCheckerService = parkingCheckerService;
    }

    @Override
    public boolean check(String vehicleIdStr) {
        final NumberPlate numberPlate = new NumberPlate(vehicleIdStr);
        return parkingCheckerService.check(numberPlate);
    }
}
