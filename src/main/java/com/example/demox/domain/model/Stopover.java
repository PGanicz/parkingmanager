package com.example.demox.domain.model;

import java.util.Currency;
import java.util.Date;

public class Stopover {
    private StopoverId stopoverId;
    private VehicleId vehicleId;
    private DriverId driverId;
    private Date arrival;
    private Date departure;

    public Stopover(StopoverId stopoverId, VehicleId vehicleId, DriverId driverId, Date arrival) {
        this.stopoverId = stopoverId;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.arrival = arrival;
    }

    public StopoverId stopoverId() {
        return stopoverId;
    }
    public boolean isCompleted() {
        return departure == null;
    }

    public void setDepartureDate(Date departure) {
        this.departure = departure;
    }
    public Fee getFee(String currency, FeeStrategy strategy) {
        return strategy.calculateFee(new Long(2));
    }
}
