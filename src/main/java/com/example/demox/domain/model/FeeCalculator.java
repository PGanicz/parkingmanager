package com.example.demox.domain.model;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;

public class FeeCalculator {

    private FeeStrategy feeStrategy;
    public FeeCalculator(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public void setFeeStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public Fee calculateFee(Stopover stopover) {
        if (stopover.isCompleted()) {

        }

        return feeStrategy.calculateFee(new Long(3));
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
