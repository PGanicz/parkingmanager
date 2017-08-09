package com.example.demox.domain.model;

import java.util.Date;

public class FeeCalculator {

    private FeeCalculationStrategy feeCalculationStrategy;
    public FeeCalculator(FeeCalculationStrategy feeCalculationStrategy) {
        this.feeCalculationStrategy = feeCalculationStrategy;
    }

    public void setFeeCalculationStrategy(FeeCalculationStrategy feeCalculationStrategy) {
        this.feeCalculationStrategy = feeCalculationStrategy;
    }

    public Fee calculateFee(Stopover stopover) {
        if (stopover.isCompleted()) {

        }

        return feeCalculationStrategy.calculateFee(new Long(3));
    }
    private Long calculateHoursBetweenDates(Date start, Date end) {
        final long elapsedTime = (end.getTime() - start.getTime());
        final long sec = elapsedTime / 1000;
        final long minutes = sec / 60;
        final long hours = minutes / 60;

        if (minutes == 0 && sec == 0) {
            return hours;
        }
        return hours + 1;
    }
}
