package com.example.parkingmanager.domain.model.ticket;

import java.util.List;

public interface TicketRepository {
    TicketId nextTicketId();
    void store(Ticket ticket);
    List<Ticket> findAll();
    void delete(Ticket ticket);
    Ticket findById(TicketId id);
}
