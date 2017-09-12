package com.example.parkingmanager.interfaces.facade;

import com.example.parkingmanager.domain.model.ticket.UnknownTicketException;
import com.example.parkingmanager.interfaces.facade.dto.FeeDTO;
import com.example.parkingmanager.interfaces.facade.dto.TicketDTO;

public interface ParkingMeterServiceFacade {
    TicketDTO createNewTicket(String driverId, String numberPlate);

    void payAFee(String ticketId) throws UnknownTicketException;

    FeeDTO getCurrentFee(String ticketId) throws UnknownTicketException;
}
