package com.example.demox.domain.model.ticket;

import com.example.demox.domain.model.driver.DriverId;

import java.util.Date;

public class Ticket {
    private TicketId ticketId;
    private DriverId driverId;
    private Date creationDate;

    public Ticket(TicketId ticketId, DriverId driverId, Date creationDate) {
        this.ticketId = ticketId;
        this.driverId = driverId;
        this.creationDate = creationDate;
    }

    public TicketId getTicketId() {
        return ticketId;
    }

    public void setTicketId(TicketId ticketId) {
        this.ticketId = ticketId;
    }

    public DriverId getDriverId() {
        return driverId;
    }

    public void setDriverId(DriverId driverId) {
        this.driverId = driverId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
