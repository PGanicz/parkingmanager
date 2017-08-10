package com.example.demox.application;

import com.example.demox.domain.model.driver.DriverId;
import com.example.demox.domain.model.payment.Fee;
import com.example.demox.domain.model.stepover.StopoverId;


public interface ParkingMeterService {
    StopoverId registerNewStopover(final DriverId driverId);

    void registerEndOfStopover(StopoverId stopoverId);

    Fee getCurrentFee(StopoverId stopoverId);
}

