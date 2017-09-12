package com.example.parkingmanager.domain.model.ticket;

public class UnknownTicketException extends Exception {

    private final TicketId ticketId;

    public UnknownTicketException(final TicketId ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public String getMessage() {
        return "No ticket with id " + ticketId.toString() + " exists in the system";
    }
}
