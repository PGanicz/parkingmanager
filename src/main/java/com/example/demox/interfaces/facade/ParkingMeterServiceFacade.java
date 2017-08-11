package com.example.demox.interfaces.facade;

import com.example.demox.domain.model.ticket.UnknownTicketException;
import com.example.demox.interfaces.facade.dto.FeeDTO;
import com.example.demox.interfaces.facade.dto.TicketDTO;

public interface ParkingMeterServiceFacade {
    TicketDTO createNewTicket(String driverId, String numberPlate);

    void payAFee(String ticketId) throws UnknownTicketException;

    FeeDTO getCurrentFee(String ticketId) throws UnknownTicketException;
}
