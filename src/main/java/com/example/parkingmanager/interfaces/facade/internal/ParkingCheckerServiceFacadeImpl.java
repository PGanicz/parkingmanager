package com.example.parkingmanager.interfaces.facade.internal;

import com.example.parkingmanager.application.ParkingCheckerService;
import com.example.parkingmanager.domain.model.ticket.NumberPlate;
import com.example.parkingmanager.interfaces.facade.ParkingCheckerServiceFacade;
import com.example.parkingmanager.interfaces.facade.dto.StateDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class ParkingCheckerServiceFacadeImpl implements ParkingCheckerServiceFacade {
    private ParkingCheckerService parkingCheckerService;

    @Autowired
    public void setParkingCheckerService(ParkingCheckerService parkingCheckerService) {
        this.parkingCheckerService = parkingCheckerService;
    }

    @Override
    public StateDTO getState(String numberPlateStr) {
        final NumberPlate numberPlate = new NumberPlate(numberPlateStr);
        return new StateDTO(parkingCheckerService.hasTicket(numberPlate));
    }
}
