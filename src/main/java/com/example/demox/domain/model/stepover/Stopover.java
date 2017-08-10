package com.example.demox.domain.model.stepover;

import com.example.demox.domain.model.driver.DriverId;

import java.util.Date;

public class Stopover {
    private StopoverId stopoverId;
    private NumberPlate numberPlate;
    private DriverId driverId;
    private Date arrival;
    private Date departure;

    public Stopover(StopoverId stopoverId, NumberPlate numberPlate, DriverId driverId, Date arrival) {
        this.stopoverId = stopoverId;
        this.numberPlate = numberPlate;
        this.driverId = driverId;
        this.arrival = arrival;
    }

    public Date getArrival() {
        return arrival;
    }

    public Date getDeparture() {
        return departure;
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

    public DriverId getDriverId() {
        return driverId;
    }
}
