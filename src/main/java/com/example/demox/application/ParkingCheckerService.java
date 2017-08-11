package com.example.demox.application;

import com.example.demox.domain.model.ticket.NumberPlate;

public interface ParkingCheckerService {
    boolean hasTicket(final NumberPlate numberPlate);
}
