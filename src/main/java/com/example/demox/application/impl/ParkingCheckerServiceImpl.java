package com.example.demox.application.impl;

import com.example.demox.application.ParkingCheckerService;
import com.example.demox.domain.model.ticket.NumberPlate;
import com.example.demox.domain.model.ticket.Ticket;
import com.example.demox.domain.model.ticket.TicketId;
import com.example.demox.domain.model.ticket.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ParkingCheckerServiceImpl implements ParkingCheckerService {

    private TicketRepository ticketRepository;

    @Autowired
    public void setTicketRepository(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public boolean hasTicket(NumberPlate numberPlate) {
        Optional<Ticket> result = ticketRepository.findAll()
                .stream()
                .filter(ticket -> ticket.getNumberPlate().equals(numberPlate))
                .findAny();
        return result.isPresent();
    }
}
