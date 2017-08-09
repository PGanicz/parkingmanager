package com.example.demox.interfaces.facade;

import com.example.demox.domain.model.VehicleId;

public interface ParkingCheckerServiceFacade {
    boolean check(final String vehicleId);
}
