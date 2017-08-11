package com.example.demox.domain.model.ticket;

public interface TicketRepository {
    TicketId nextTicketId();
    void store(Ticket ticket);
    void update(Ticket ticket);
    void delete(Ticket ticket);
    Ticket findById(TicketId id);
}
