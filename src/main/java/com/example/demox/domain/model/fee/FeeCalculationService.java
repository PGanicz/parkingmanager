package com.example.demox.domain.model.fee;

import com.example.demox.domain.model.driver.Driver;
import com.example.demox.domain.model.ticket.Ticket;

import java.math.BigDecimal;
import java.util.Date;

public class FeeCalculationService {

    public static Fee countFee(Ticket ticket, Date end, Driver driver) {
        Date start = ticket.getCreationDate();
        Fee fee = countFee(start, end, driver);
        fee.setPaymentTime(end);
        return fee;
    }

    public static Fee countFee(Date start, Date end, Driver driver) {
        int hoursBetween = calculateHoursBetweenDates(start, end);
        BigDecimal price;
        if (driver.getType() == Driver.Type.VIP) {
            price = sumOfGeomSeries(2 ,1.5, hoursBetween - 1);
        } else {
            price = sumOfGeomSeries(1, 2, hoursBetween);
        }
        return new Fee(price, "PLN");
    }

    private static int calculateHoursBetweenDates(Date start, Date end) {
        final long elapsedTime = (end.getTime() - start.getTime());
        final long sec = elapsedTime / 1000;
        final long minutes = sec / 60;
        final long hours = minutes / 60;

        if (minutes == 0 && sec == 0) {
            return (int)hours;
        }
        return (int)(hours + 1);
    }

    private static BigDecimal sumOfGeomSeries(double first, double ratio, int terms) {
        final BigDecimal qFactor = new BigDecimal(ratio);
        final BigDecimal nthValue = qFactor.pow(terms);
        final BigDecimal one = new BigDecimal(1);
        final BigDecimal firstValue = new BigDecimal(first);
        final BigDecimal geometricSum = ((one.subtract(nthValue)).divide(one.subtract(qFactor))).multiply(firstValue);

        return geometricSum;
    }
}
