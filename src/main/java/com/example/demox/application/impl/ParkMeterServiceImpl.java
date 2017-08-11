package com.example.demox.application.impl;

import com.example.demox.application.ParkMeterService;
import com.example.demox.domain.model.driver.Driver;
import com.example.demox.domain.model.driver.DriverId;
import com.example.demox.domain.model.driver.DriverRepository;
import com.example.demox.domain.model.fee.Fee;
import com.example.demox.domain.model.fee.FeeCalculationService;
import com.example.demox.domain.model.fee.FeeRepository;
import com.example.demox.domain.model.clock.ClockService;
import com.example.demox.domain.model.ticket.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

public class ParkMeterServiceImpl implements ParkMeterService {

    private TicketRepository ticketRepository;
    private DriverRepository driverRepository;
    private FeeRepository feeRepository;
    private ClockService clockService;
    private FeeCalculationService feeCalculationService;

    @Autowired
    public void setFeeCalculationService(FeeCalculationService feeCalculationService) {
        this.feeCalculationService = feeCalculationService;
    }

    @Autowired
    public void setClockService(ClockService clockService) {
        this.clockService = clockService;
    }

    @Autowired
    public void setTicketRepository(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Autowired
    public void setDriverRepository(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Autowired
    public void setFeeRepository(FeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }

    @Override
    public Ticket createNewTicket(final DriverId driverId, final NumberPlate numberPlate) {
        final TicketId ticketId = ticketRepository.nextTicketId();
        final Date creationDate = clockService.getCurrentDate();

        final Ticket ticket = new Ticket(ticketId, numberPlate, driverId, creationDate);

        ticketRepository.store(ticket);
        return ticket;
    }

    @Override
    public void payAFee(final TicketId ticketId) throws UnknownTicketException {
        final Date completionDate = clockService.getCurrentDate();
        final Ticket ticket = ticketRepository.findById(ticketId);
        if (ticket == null) {
            throw new UnknownTicketException(ticketId);
        }

        Driver driver = driverRepository.find(ticket.getDriverId());
        if (driver == null) {
            driver = new Driver(ticket.getDriverId(), Driver.Type.REGULAR);
        }
        Fee fee = feeCalculationService.countFee(ticket, completionDate, driver);
        feeRepository.store(fee);

        ticketRepository.delete(ticket);
    }


    @Override
    public Fee getCurrentFee(TicketId ticketId) throws UnknownTicketException {
        final Date currentDate = clockService.getCurrentDate();
        final Ticket ticket = ticketRepository.findById(ticketId);
        if (ticket == null) {
            throw new UnknownTicketException(ticketId);
        }
        Driver driver = driverRepository.find(ticket.getDriverId());
        if (driver == null) {
            driver = new Driver(ticket.getDriverId(), Driver.Type.REGULAR);
        }
        Fee fee = feeCalculationService.countFee(ticket, currentDate, driver);
        return fee;
    }
}
