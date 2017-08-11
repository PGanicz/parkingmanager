package com.example.parkingmanager.domain.model.ticket;

public class TicketId {
    private String id;
    public TicketId(String id) {
        this.id = id;
    }

    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketId ticketId = (TicketId) o;

        return id != null ? id.equals(ticketId.id) : ticketId.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
