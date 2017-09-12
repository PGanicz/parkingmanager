package com.example.parkingmanager.application;

import com.example.parkingmanager.domain.model.ticket.NumberPlate;

public interface ParkingCheckerService {
    boolean hasTicket(final NumberPlate numberPlate);
}
