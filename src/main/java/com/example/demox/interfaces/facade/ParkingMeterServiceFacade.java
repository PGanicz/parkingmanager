package com.example.demox.interfaces.facade;

import com.example.demox.interfaces.facade.dto.FeeDTO;
import com.example.demox.interfaces.facade.dto.TicketDTO;

public interface ParkingMeterServiceFacade {
    TicketDTO createNewTicket(String driverId, String numberPlate);

    void payAFee(String ticketId);

    FeeDTO getCurrentFee(String ticketId);
}
