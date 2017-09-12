package com.example.parkingmanager.infrastructure;

import com.example.parkingmanager.domain.model.driver.Driver;
import com.example.parkingmanager.domain.model.driver.DriverId;
import com.example.parkingmanager.domain.model.driver.DriverRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DriverRepositoryImpl implements DriverRepository {

    public static Map<DriverId, Driver> inMemoryBase = new ConcurrentHashMap<>();
    @Override
    public Driver find(DriverId driver) {
        return inMemoryBase.get(driver);
    }

    @Override
    public void store(Driver driver) {
        inMemoryBase.put(driver.getDriverId(), driver);
    }
}
