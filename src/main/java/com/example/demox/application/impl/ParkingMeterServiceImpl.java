package com.example.demox.application.impl;

import com.example.demox.application.ParkingMeterService;
import com.example.demox.domain.model.driver.Driver;
import com.example.demox.domain.model.driver.DriverId;
import com.example.demox.domain.model.driver.DriverRepository;
import com.example.demox.domain.model.payment.Fee;
import com.example.demox.domain.model.payment.FeeCalculationService;
import com.example.demox.domain.model.payment.FeeRepository;
import com.example.demox.domain.model.service.ClockService;
import com.example.demox.domain.model.stepover.Stopover;
import com.example.demox.domain.model.stepover.StopoverId;
import com.example.demox.domain.model.stepover.StopoverRepository;

import com.example.demox.domain.model.stepover.UnknownStopoverException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ParkingMeterServiceImpl implements ParkingMeterService {

    private StopoverRepository stopoverRepository;
    private DriverRepository driverRepository;
    private FeeRepository feeRepository;
    private ClockService clockService;

    @Autowired
    public void setClockService(ClockService clockService) {
        this.clockService = clockService;
    }

    @Autowired
    public void setStopoverRepository(StopoverRepository stopoverRepository) {
        this.stopoverRepository = stopoverRepository;
    }

    @Autowired
    public void setDriverRepository(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Autowired
    public void setFeeRepository(FeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }

    @Override
    public StopoverId registerNewStopover(final DriverId driverId) {
        final StopoverId stopoverId = stopoverRepository.nextStopoverId();
        final Date registrationDate = clockService.getCurrentDate();
        final Stopover stopover = new Stopover(stopoverId, driverId, registrationDate);

        stopoverRepository.store(stopover);

        return stopover.stopoverId();
    }

    @Override
    public void registerEndOfStopover(final StopoverId stopoverId) throws UnknownStopoverException {
        final Date completionDate = clockService.getCurrentDate();
        final Stopover stopover = stopoverRepository.findById(stopoverId);
        if (stopover == null) {
            throw new UnknownStopoverException(stopoverId);
        }
        Driver driver = driverRepository.find(stopover.getDriverId());
        if (driver == null) {
            driver = new Driver(stopover.getDriverId(), Driver.Type.REGULAR);
        }

        stopover.setDepartureDate(completionDate);

        stopoverRepository.update(stopover);

        Fee fee = FeeCalculationService.countFee(stopover,
                                                 driver);
        feeRepository.store(fee);
    }

    @Override
    public Fee getCurrentFee(StopoverId stopoverId) throws UnknownStopoverException {
        final Stopover stopover = stopoverRepository.findById(stopoverId);
        if (stopover == null) {
            throw new UnknownStopoverException(stopoverId);
        }
        final Driver driver = driverRepository.find(stopover.getDriverId());

        return FeeCalculationService.countFee(stopover, driver);
    }
}
