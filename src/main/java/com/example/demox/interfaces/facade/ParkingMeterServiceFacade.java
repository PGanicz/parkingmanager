package com.example.demox.interfaces.facade;

import com.example.demox.interfaces.facade.dto.TicketDTO;

public interface ParkingMeterServiceFacade {
    TicketDTO createNewTicket(String driverId);

    void payAFee(String ticketId);

    String getCurrentFee(String ticketId);
}
