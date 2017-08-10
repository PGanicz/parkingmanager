package com.example.demox.application;

import com.example.demox.domain.model.driver.DriverId;
import com.example.demox.domain.model.stepover.StopoverId;
import com.example.demox.domain.model.stepover.NumberPlate;

public interface ParkingMeterService {
    StopoverId registerNewStopover(final DriverId driverId,
                                   final NumberPlate id);
    void registerEndOfStopover(StopoverId stopoverId);
}
