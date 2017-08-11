package com.example.parkingmanager.domain.model.fee;

import com.example.parkingmanager.domain.model.driver.Driver;
import com.example.parkingmanager.domain.model.driver.DriverId;
import com.example.parkingmanager.domain.model.ticket.NumberPlate;
import com.example.parkingmanager.domain.model.ticket.Ticket;
import com.example.parkingmanager.domain.model.ticket.TicketId;
import junit.framework.TestCase;

import java.math.BigDecimal;
import java.util.Date;



public class FeeCalculationServiceTest extends TestCase {

    private Driver vipDriver;
    private Driver regularDriver;
    private Ticket ticket;
    private Date creationTime;
    private Date startetFirstHour;
    private Date hourLater;
    private Date twoHourAndHalfLater;
    private Date dayLater;
    private FeeCalculationService feeCalculationService;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        feeCalculationService = new FeeCalculationService();
        vipDriver = new Driver(new DriverId("driverId1"), Driver.Type.VIP);
        regularDriver = new Driver(new DriverId("driverId2"), Driver.Type.REGULAR);
        this.creationTime = new Date();
        this.hourLater = new Date(this.creationTime.getTime() + (1000 * 60 * 60));
        this.startetFirstHour = new Date(this.creationTime.getTime() + 1000);
        this.twoHourAndHalfLater = new Date(this.creationTime.getTime() + (long)(2.5 *(1000 * 60 * 60)));
        this.dayLater = new Date(this.creationTime.getTime() + 24*(1000 * 60 * 60));

        ticket = new Ticket(new TicketId("TicketId"), new NumberPlate("NumberPlate"), new DriverId("fads"), creationTime);
    }

    public void testFeeCalculationForVipDriver() throws Exception {
        Fee fee = feeCalculationService.countFee(ticket, creationTime, regularDriver);
        assertEquals(new BigDecimal(0), fee.getFine());

        fee = feeCalculationService.countFee(ticket, creationTime, vipDriver);
        assertEquals(new BigDecimal(0), fee.getFine());

        fee = feeCalculationService.countFee(ticket, hourLater, vipDriver);
        assertEquals(new BigDecimal(2), fee.getFine());

        fee = feeCalculationService.countFee(ticket, twoHourAndHalfLater, vipDriver);
        assertEquals(new BigDecimal("5.0"), fee.getFine());

        fee = feeCalculationService.countFee(ticket, dayLater, vipDriver);
        assertEquals(new BigDecimal("67332.44878411293029785156250"), fee.getFine());
    }

    public void testFeeCalculationForRegularDriver() throws Exception {
        Fee fee = feeCalculationService.countFee(ticket, creationTime, regularDriver);
        assertEquals(new BigDecimal(0), fee.getFine());

        fee = feeCalculationService.countFee(ticket, startetFirstHour, regularDriver);
        assertEquals(new BigDecimal(1), fee.getFine());

        fee = feeCalculationService.countFee(ticket, hourLater, regularDriver);
        assertEquals(new BigDecimal(3), fee.getFine());

        fee = feeCalculationService.countFee(ticket, twoHourAndHalfLater, regularDriver);
        assertEquals(new BigDecimal(7), fee.getFine());

        fee = feeCalculationService.countFee(ticket, dayLater, regularDriver);
        assertEquals(new BigDecimal(33554431), fee.getFine());
    }

}
