package com.example.demox.interfaces.facade.internal;

import com.example.demox.application.ParkingCheckerService;
import com.example.demox.domain.model.ticket.TicketId;
import com.example.demox.interfaces.facade.ParkingCheckerServiceFacade;
import com.example.demox.interfaces.facade.dto.StateDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class ParkingCheckerServiceFacadeImpl implements ParkingCheckerServiceFacade {
    private ParkingCheckerService parkingCheckerService;

    @Autowired
    public void setParkingCheckerService(ParkingCheckerService parkingCheckerService) {
        this.parkingCheckerService = parkingCheckerService;
    }

    @Override
    public StateDTO getState(String ticketIdStr) {
        final TicketId ticketId = new TicketId(ticketIdStr);
        return new StateDTO(parkingCheckerService.getState(ticketId));
    }
}
