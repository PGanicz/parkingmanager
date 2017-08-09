package com.example.demox.interfaces.facade.internal;

import com.example.demox.application.ParkingMeterService;
import com.example.demox.domain.model.DriverId;
import com.example.demox.domain.model.Stopover;
import com.example.demox.domain.model.StopoverId;
import com.example.demox.domain.model.VehicleId;
import com.example.demox.interfaces.facade.ParkingMeterServiceFacade;
import javafx.scene.layout.VBox;
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
        final VehicleId vehicleId = new VehicleId(vehicleIdStr);
        final StopoverId stopoverId =
                parkingMeterService.registerNewStopover(driverId, vehicleId);

        return stopoverId.getId();
    }

    @Override
    public void registerEndOfStopover(String stopoverIdStr) {
        final StopoverId stopoverId = new StopoverId(stopoverIdStr);

        parkingMeterService.registerEndOfStopover(stopoverId);
    }
}
