package com.example.parkingmanager.domain.model.driver;

public interface DriverRepository {
    Driver find(DriverId driver);
    void store(Driver driver);
}
