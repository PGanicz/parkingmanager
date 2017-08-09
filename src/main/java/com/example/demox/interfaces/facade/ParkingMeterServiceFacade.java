package com.example.demox.interfaces.facade;

import com.example.demox.domain.model.StopoverId;

public interface ParkingMeterServiceFacade {
    String registerNewStopover(String VehicleId, String driverId);

    void registerEndOfStopover(String stopoverId);
}
