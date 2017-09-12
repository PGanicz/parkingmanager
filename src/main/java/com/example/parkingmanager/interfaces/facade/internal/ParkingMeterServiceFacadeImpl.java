package com.example.parkingmanager.interfaces.facade.internal;

import com.example.parkingmanager.application.ParkMeterService;
import com.example.parkingmanager.domain.model.driver.DriverId;
import com.example.parkingmanager.domain.model.fee.Fee;
import com.example.parkingmanager.domain.model.ticket.NumberPlate;
import com.example.parkingmanager.domain.model.ticket.Ticket;
import com.example.parkingmanager.domain.model.ticket.TicketId;
import com.example.parkingmanager.domain.model.ticket.UnknownTicketException;
import com.example.parkingmanager.interfaces.facade.ParkingMeterServiceFacade;
import com.example.parkingmanager.interfaces.facade.dto.FeeDTO;
import com.example.parkingmanager.interfaces.facade.dto.TicketDTO;
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
