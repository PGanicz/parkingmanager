package com.example.demox.domain.model.driver;

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
}
