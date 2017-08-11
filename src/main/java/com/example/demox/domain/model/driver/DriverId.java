package com.example.demox.domain.model.driver;

public class DriverId {
    String id;

    public DriverId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DriverId driverId = (DriverId) o;

        return id != null ? id.equals(driverId.id) : driverId.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
