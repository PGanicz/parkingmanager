package com.example.demox.application.impl;

import com.example.demox.application.ParkingCheckerService;
import com.example.demox.domain.model.ticket.TicketId;
import com.example.demox.domain.model.ticket.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ParkingCheckerServiceImpl implements ParkingCheckerService {

    private TicketRepository ticketRepository;

    @Autowired
    public void setTicketRepository(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public boolean getState(TicketId ticketId) {
        return false;
    }
}
