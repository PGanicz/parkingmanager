package com.example.demox.application;

import com.example.demox.domain.model.driver.DriverId;
import com.example.demox.domain.model.payment.Fee;
import com.example.demox.domain.model.stepover.StopoverId;
import com.example.demox.domain.model.stepover.UnknownStopoverException;


public interface ParkingMeterService {
    StopoverId registerNewStopover(final DriverId driverId);

    void registerEndOfStopover(StopoverId stopoverId) throws UnknownStopoverException;

    Fee getCurrentFee(StopoverId stopoverId) throws UnknownStopoverException;
}

