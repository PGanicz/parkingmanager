package com.example.parkingmanager.interfaces.facade.dto;

import com.example.parkingmanager.domain.model.ticket.Ticket;

import java.text.SimpleDateFormat;

public class TicketDTO {
    private String ticketId;
    private String creationTime;
    public TicketDTO(Ticket ticket) {
        this.ticketId = ticket.getTicketId().toString();
        SimpleDateFormat dt = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        this.creationTime = dt.format(ticket.getCreationDate());
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getCreationTime() {
        return creationTime;
    }
}
