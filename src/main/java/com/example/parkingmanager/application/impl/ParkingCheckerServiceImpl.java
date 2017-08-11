package com.example.parkingmanager.application.impl;

import com.example.parkingmanager.application.ParkingCheckerService;
import com.example.parkingmanager.domain.model.ticket.NumberPlate;
import com.example.parkingmanager.domain.model.ticket.Ticket;
import com.example.parkingmanager.domain.model.ticket.TicketRepository;
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
