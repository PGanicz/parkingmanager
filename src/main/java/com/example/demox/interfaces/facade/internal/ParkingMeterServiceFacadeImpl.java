package com.example.demox.interfaces.facade.internal;

import com.example.demox.application.ParkingMeterService;
import com.example.demox.domain.model.driver.DriverId;
import com.example.demox.domain.model.payment.Fee;
import com.example.demox.domain.model.stepover.StopoverId;
import com.example.demox.domain.model.stepover.NumberPlate;
import com.example.demox.interfaces.facade.ParkingMeterServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;

public class ParkingMeterServiceFacadeImpl implements ParkingMeterServiceFacade {
    private ParkingMeterService parkingMeterService;

    @Autowired
    public void setParkingMeterService(ParkingMeterService parkingMeterService) {
        this.parkingMeterService = parkingMeterService;
    }

    @Override
    public String registerNewStopover(String vehicleIdStr, String driverIdStr) {
        final DriverId driverId = new DriverId(driverIdStr);
        final NumberPlate numberPlate = new NumberPlate(vehicleIdStr);
        final StopoverId stopoverId =
                parkingMeterService.registerNewStopover(driverId, numberPlate);

        return stopoverId.getId();
    }

    @Override
    public void registerEndOfStopover(String stopoverIdStr) {
        final StopoverId stopoverId = new StopoverId(stopoverIdStr);

        parkingMeterService.registerEndOfStopover(stopoverId);
    }

    @Override
    public String getCurrentFee(String stopoverIdStr) {
        final StopoverId stopoverId = new StopoverId(stopoverIdStr);
        Fee fee = parkingMeterService.getCurrentFee(stopoverId);
        return fee.getFine().longValue() + " " +  fee.getCurrency();
    }
}
