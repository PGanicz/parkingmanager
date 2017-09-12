package com.example.parkingmanager.application;

import com.example.parkingmanager.domain.model.driver.DriverId;
import com.example.parkingmanager.domain.model.fee.Fee;
import com.example.parkingmanager.domain.model.ticket.NumberPlate;
import com.example.parkingmanager.domain.model.ticket.Ticket;
import com.example.parkingmanager.domain.model.ticket.TicketId;
import com.example.parkingmanager.domain.model.ticket.UnknownTicketException;


public interface ParkMeterService {
    Ticket createNewTicket(final DriverId driverId, final NumberPlate numberPlate);

    void payAFee(TicketId stopoverId) throws UnknownTicketException;

    Fee getCurrentFee(TicketId stopoverId) throws UnknownTicketException;
}

