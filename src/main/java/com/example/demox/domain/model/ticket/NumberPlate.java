package com.example.demox.domain.model.ticket;

public class NumberPlate {
    private String numberPlate;

    public NumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String toString() {
        return numberPlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumberPlate that = (NumberPlate) o;

        return numberPlate != null ? numberPlate.equals(that.numberPlate) : that.numberPlate == null;
    }

    @Override
    public int hashCode() {
        return numberPlate != null ? numberPlate.hashCode() : 0;
    }
}
