package com.example.demox.application.impl;

import com.example.demox.application.ParkingMeterService;
import com.example.demox.domain.model.*;

import java.util.Date;

public class ParkingMeterServiceImpl implements ParkingMeterService {

    private StopoverRepository stopoverRepository;

    public void setStopoverRepository(StopoverRepository stopoverRepository) {
        this.stopoverRepository = stopoverRepository;
    }

    @Override
    public StopoverId registerNewStopover(final DriverId driverId,
                                          final VehicleId vehicleId) {
        final StopoverId stopoverId = stopoverRepository.nextStopoverId();

        final Stopover stopover = new Stopover(stopoverId, vehicleId, driverId, new Date());

        stopoverRepository.store(stopover);

        return stopover.stopoverId();
    }

    @Override
    public void registerEndOfStopover(final StopoverId stopoverId) {
        Stopover stopover = stopoverRepository.find(stopoverId);

        stopover.setDepartureDate(new Date());

        stopoverRepository.update(stopover);
    }
}
