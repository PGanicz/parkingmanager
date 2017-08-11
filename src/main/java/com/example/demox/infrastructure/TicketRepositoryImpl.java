package com.example.demox.infrastructure;

import com.example.demox.domain.model.ticket.Ticket;

import com.example.demox.domain.model.ticket.TicketId;
import com.example.demox.domain.model.ticket.TicketRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TicketRepositoryImpl implements TicketRepository {
    public static Map<TicketId, Ticket> inMemoryBase = new ConcurrentHashMap<>();

    public TicketId nextTicketId() {
        final String rand = UUID.randomUUID().toString().toUpperCase();
        return new TicketId(rand.toString());
    }

    @Override
    public void store(Ticket ticket) {
        inMemoryBase.put(ticket.getTicketId(), ticket);
    }

    @Override
    public void delete(Ticket ticket) {
        inMemoryBase.remove(ticket.getTicketId());
    }

    @Override
    public void update(Ticket ticket) {
        inMemoryBase.put(ticket.getTicketId(), ticket);
    }

    @Override
    public List<Ticket> findAll() {
        return new ArrayList<Ticket>(inMemoryBase.values());
    }

    @Override
    public Ticket findById(TicketId id) {
        return inMemoryBase.get(id);
    }

}
