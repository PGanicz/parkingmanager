package com.example.demox.interfaces.facade;

import com.example.demox.domain.model.stepover.UnknownStopoverException;

public interface ParkingCheckerServiceFacade {
    boolean check(final String stopoverId);
}
