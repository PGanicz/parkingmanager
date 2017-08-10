package com.example.demox.application;

import com.example.demox.domain.model.driver.DriverId;
import com.example.demox.domain.model.payment.Fee;
import com.example.demox.domain.model.ticket.NumberPlate;
import com.example.demox.domain.model.ticket.Ticket;
import com.example.demox.domain.model.ticket.TicketId;
import com.example.demox.domain.model.ticket.UnknownTicketException;


public interface ParkingMeterService {
    Ticket createNewTicket(final DriverId driverId, final NumberPlate numberPlate);

    void payAFee(TicketId stopoverId) throws UnknownTicketException;

    Fee getCurrentFee(TicketId stopoverId) throws UnknownTicketException;
}

