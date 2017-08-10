package com.example.demox.application;

import com.example.demox.domain.model.stepover.NumberPlate;

public interface ParkingCheckerService {
    boolean check(final NumberPlate numberPlate);
}
