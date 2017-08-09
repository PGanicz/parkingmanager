package com.example.demox.application;

import com.example.demox.domain.model.Driver;
import com.example.demox.domain.model.DriverId;
import com.example.demox.domain.model.StopoverId;
import com.example.demox.domain.model.VehicleId;

import java.util.Date;

public interface ParkingMeterService {
    StopoverId registerNewStopover(final DriverId driverId,
                                   final VehicleId id);
    void registerEndOfStopover(StopoverId stopoverId);
}
