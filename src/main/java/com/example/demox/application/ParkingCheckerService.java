package com.example.demox.application;

import com.example.demox.domain.model.stepover.StopoverId;
import com.example.demox.domain.model.stepover.UnknownStopoverException;

public interface ParkingCheckerService {
    boolean check(final StopoverId stopoverId);
}
