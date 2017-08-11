package com.example.demox.interfaces.facade.internal;

import com.example.demox.application.ParkMeterService;
import com.example.demox.domain.model.driver.DriverId;
import com.example.demox.domain.model.fee.Fee;
import com.example.demox.domain.model.ticket.NumberPlate;
import com.example.demox.domain.model.ticket.Ticket;
import com.example.demox.domain.model.ticket.TicketId;
import com.example.demox.domain.model.ticket.UnknownTicketException;
import com.example.demox.interfaces.facade.ParkingMeterServiceFacade;
import com.example.demox.interfaces.facade.dto.FeeDTO;
import com.example.demox.interfaces.facade.dto.TicketDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class ParkingMeterServiceFacadeImpl implements ParkingMeterServiceFacade {
    private ParkMeterService parkMeterService;

    @Autowired
    public void setParkMeterService(ParkMeterService parkMeterService) {
        this.parkMeterService = parkMeterService;
    }

    @Override
    public TicketDTO createNewTicket(String driverIdStr, String numberPlateStr) {
        final DriverId driverId = new DriverId(driverIdStr);
        final NumberPlate numberPlate = new NumberPlate(numberPlateStr);
        Ticket newTicket = parkMeterService.createNewTicket(driverId, numberPlate);
        return new TicketDTO(newTicket);
    }

    @Override
    public void payAFee(String ticketIdStr) throws UnknownTicketException {
        final TicketId ticketId = new TicketId(ticketIdStr);

        parkMeterService.payAFee(ticketId);
    }

    @Override
    public FeeDTO getCurrentFee(String ticketIdStr) throws UnknownTicketException {
        final TicketId ticketId = new TicketId(ticketIdStr);
        Fee fee = parkMeterService.getCurrentFee(ticketId);
        return new FeeDTO(fee);
    }
}
