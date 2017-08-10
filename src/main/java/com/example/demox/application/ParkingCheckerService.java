package com.example.demox.application;

import com.example.demox.domain.model.stepover.StopoverId;

public interface ParkingCheckerService {
    boolean check(final StopoverId stopoverId);
}
