package com.example.demox.interfaces.facade;

public interface ParkingMeterServiceFacade {
    String registerNewStopover(String VehicleId, String driverId);

    void registerEndOfStopover(String stopoverId);
}
