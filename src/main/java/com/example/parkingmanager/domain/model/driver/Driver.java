package com.example.parkingmanager.domain.model.driver;

public class Driver {
    private DriverId driverId;
    private Type type;

    public enum Type {
        VIP,
        REGULAR
    }

    public Type getType() {
        return type;
    }

    public Driver(DriverId driverId, Type type) {
        this.driverId = driverId;
        this.type = type;
    }

    public DriverId getDriverId() {
        return driverId;
    }
}

