package com.example.demox.application.impl;

import com.example.demox.application.ParkingMeterService;
import com.example.demox.domain.model.driver.Driver;
import com.example.demox.domain.model.driver.DriverId;
import com.example.demox.domain.model.driver.DriverRepository;
import com.example.demox.domain.model.payment.Fee;
import com.example.demox.domain.model.payment.FeeCalculationService;
import com.example.demox.domain.model.payment.FeeRepository;
import com.example.demox.domain.model.stepover.NumberPlate;
import com.example.demox.domain.model.stepover.Stopover;
import com.example.demox.domain.model.stepover.StopoverId;
import com.example.demox.domain.model.stepover.StopoverRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ParkingMeterServiceImpl implements ParkingMeterService {

    private StopoverRepository stopoverRepository;
    private DriverRepository driverRepository;
    private FeeRepository feeRepository;

    @Autowired
    public void setStopoverRepository(StopoverRepository stopoverRepository) {
        this.stopoverRepository = stopoverRepository;
    }

    @Autowired
    public void setDriverRepository(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public StopoverId registerNewStopover(final DriverId driverId,
                                          final NumberPlate numberPlate) {
        final StopoverId stopoverId = stopoverRepository.nextStopoverId();

        final Stopover stopover = new Stopover(stopoverId, numberPlate, driverId, new Date());

        stopoverRepository.store(stopover);

        return stopover.stopoverId();
    }

    @Override
    public void registerEndOfStopover(final StopoverId stopoverId) {
        final Stopover stopover = stopoverRepository.findById(stopoverId);
        final Driver driver = driverRepository.find(stopover.getDriverId());
        final Date completionDate = new Date();

        stopover.setDepartureDate(completionDate);

        stopoverRepository.update(stopover);

        Fee fee = FeeCalculationService.countFee(stopover, driver);
        feeRepository.store(fee);
    }
}
