package com.example.parkingmanager.interfaces.facade.dto;

public class StateDTO {
    String state;

    public StateDTO(boolean state) {
        this.state = state ? "That vehicle has been registered." : "Unknown vehicle.";
    }

    public String getState() {
        return state;
    }
}
