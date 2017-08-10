package com.example.demox.application.impl;

import com.example.demox.application.ParkingMeterService;
import com.example.demox.domain.model.driver.Driver;
import com.example.demox.domain.model.driver.DriverId;
import com.example.demox.domain.model.driver.DriverRepository;
import com.example.demox.domain.model.payment.Fee;
import com.example.demox.domain.model.payment.FeeCalculationService;
import com.example.demox.domain.model.payment.FeeRepository;
import com.example.demox.domain.model.clock.ClockService;
import com.example.demox.domain.model.ticket.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ParkingMeterServiceImpl implements ParkingMeterService {

    private TicketRepository ticketRepository;
    private DriverRepository driverRepository;
    private FeeRepository feeRepository;
    private ClockService clockService;

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
        final Date registrationDate = clockService.getCurrentDate();
        final Ticket ticket = new Ticket(ticketId, numberPlate, driverId, registrationDate);

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
        ticketRepository.update(ticket);

        Fee fee = FeeCalculationService.countFee(ticket,completionDate, driver);
        feeRepository.store(fee);
    }


    @Override
    public Fee getCurrentFee(TicketId stopoverId) throws UnknownTicketException {
        final Date currentDate = clockService.getCurrentDate();
        final Ticket ticket = ticketRepository.findById(stopoverId);
        if (ticket == null) {
            throw new UnknownTicketException(stopoverId);
        }
        Driver driver = driverRepository.find(ticket.getDriverId());
        if (driver == null) {
            driver = new Driver(ticket.getDriverId(), Driver.Type.REGULAR);
        }
        return FeeCalculationService.countFee(ticket,currentDate, driver);
    }
}
