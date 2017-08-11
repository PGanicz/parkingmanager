package com.example.parkingmanager.interfaces.facade;

import com.example.parkingmanager.interfaces.facade.dto.StateDTO;

public interface ParkingCheckerServiceFacade {
    StateDTO getState(final String numberPlate);
}
