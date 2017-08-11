package com.example.demox.interfaces.facade;

import com.example.demox.interfaces.facade.dto.StateDTO;

public interface ParkingCheckerServiceFacade {
    StateDTO getState(final String numberPlate);
}
