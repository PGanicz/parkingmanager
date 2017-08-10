package com.example.demox.interfaces.facade;

public interface ParkingMeterServiceFacade {
    String registerNewStopover(String driverId);

    void registerEndOfStopover(String stopoverId);

    String getCurrentFee(String stopoverId);
}
