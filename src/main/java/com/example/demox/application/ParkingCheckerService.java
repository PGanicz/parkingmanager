package com.example.demox.application;

import com.example.demox.domain.model.VehicleId;

public interface ParkingCheckerService {
    boolean check(final VehicleId vehicleId);
}
