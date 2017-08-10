package com.example.demox.domain.model.stepover;

public class UnknownStopoverException extends Exception {

    private final StopoverId stopoverId;

    public UnknownStopoverException(final StopoverId stopoverId) {
        this.stopoverId = stopoverId;
    }

    @Override
    public String getMessage() {
        return "No stopover with stopover id " + stopoverId.getId() + "exists in the system";
    }
}
