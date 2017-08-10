package com.example.demox.interfaces.facade.internal;

import com.example.demox.application.ParkingMeterService;
import com.example.demox.domain.model.driver.DriverId;
import com.example.demox.domain.model.payment.Fee;
import com.example.demox.domain.model.stepover.StopoverId;
import com.example.demox.domain.model.stepover.UnknownStopoverException;
import com.example.demox.interfaces.facade.ParkingMeterServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;

public class ParkingMeterServiceFacadeImpl implements ParkingMeterServiceFacade {
    private ParkingMeterService parkingMeterService;

    @Autowired
    public void setParkingMeterService(ParkingMeterService parkingMeterService) {
        this.parkingMeterService = parkingMeterService;
    }

    @Override
    public String registerNewStopover(String driverIdStr) {
        final DriverId driverId = new DriverId(driverIdStr);
        final StopoverId stopoverId =
                parkingMeterService.registerNewStopover(driverId);

        return stopoverId.getId();
    }

    @Override
    public void registerEndOfStopover(String stopoverIdStr) {
        final StopoverId stopoverId = new StopoverId(stopoverIdStr);

        try {
            parkingMeterService.registerEndOfStopover(stopoverId);
        } catch (UnknownStopoverException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getCurrentFee(String stopoverIdStr) {
        final StopoverId stopoverId = new StopoverId(stopoverIdStr);
        Fee fee = null;
        try {
            fee = parkingMeterService.getCurrentFee(stopoverId);
        } catch (UnknownStopoverException e) {
            e.printStackTrace();
        }
        return fee.getFine().longValue() + " " +  fee.getCurrency();
    }
}
