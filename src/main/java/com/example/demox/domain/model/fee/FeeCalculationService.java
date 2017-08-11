package com.example.demox.domain.model.fee;

import com.example.demox.domain.model.driver.Driver;
import com.example.demox.domain.model.ticket.Ticket;

import java.math.BigDecimal;
import java.util.Date;

import static com.example.demox.domain.model.driver.Driver.Type.VIP;

public class FeeCalculationService {

    public Fee countFee(Ticket ticket, Date end, Driver driver) {
        final Date start = ticket.getCreationDate();
        int hoursBetween = calculateHoursBetweenDates(start, end);
        BigDecimal price = driver.getType() == VIP ? countFeeForVip(hoursBetween) :
                countFeeForRegular(hoursBetween);
        return new Fee(price, "PLN", end);
    }

    private BigDecimal countFeeForVip(int stopoverDurationInHours) {
        if (stopoverDurationInHours == 0)
            return new BigDecimal("0");
        return sumOfGeomSeries(2, 1.5, stopoverDurationInHours -1);
    }

    private BigDecimal countFeeForRegular(int stopoverDurationInHours) {
        return sumOfGeomSeries(1, 2, stopoverDurationInHours);
    }

    private int calculateHoursBetweenDates(Date start, Date end) {
        final long elapsedTime = (end.getTime() - start.getTime());
        final long sec = elapsedTime / 1000;
        final long minutes = sec / 60;
        final long hours = minutes / 60;

        if (minutes == 0 && sec == 0) {
            return (int)hours;
        }
        return (int)(hours + 1);
    }

    private BigDecimal sumOfGeomSeries(double first, double ratio, int terms) {
        final BigDecimal qFactor = new BigDecimal(ratio);
        final BigDecimal nthValue = qFactor.pow(terms);
        final BigDecimal one = new BigDecimal(1);
        final BigDecimal firstValue = new BigDecimal(first);
        final BigDecimal geometricSum = ((one.subtract(nthValue)).divide(one.subtract(qFactor))).multiply(firstValue);

        return geometricSum;
    }
}
