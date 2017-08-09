package com.example.demox.domain.model;

public class Driver {
    private DriverId driverId;
    private Type type;

    public enum Type {
        VIP,
        REGULAR
    }
}
