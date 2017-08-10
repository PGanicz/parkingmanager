package com.example.demox.application;

import com.example.demox.domain.model.ticket.TicketId;

public interface ParkingCheckerService {
    boolean getState(final TicketId ticketId);
}
