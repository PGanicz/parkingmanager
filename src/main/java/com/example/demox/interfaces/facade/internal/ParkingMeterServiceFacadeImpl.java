package com.example.demox.interfaces.facade.internal;

import com.example.demox.application.ParkingMeterService;
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
    private ParkingMeterService parkingMeterService;

    @Autowired
    public void setParkingMeterService(ParkingMeterService parkingMeterService) {
        this.parkingMeterService = parkingMeterService;
    }

    @Override
    public TicketDTO createNewTicket(String driverIdStr, String numberPlateStr) {
        final DriverId driverId = new DriverId(driverIdStr);
        final NumberPlate numberPlate = new NumberPlate(numberPlateStr);
        Ticket newTicket = parkingMeterService.createNewTicket(driverId, numberPlate);
        return new TicketDTO(newTicket);
    }

    @Override
    public void payAFee(String ticketIdStr) {
        final TicketId ticketId = new TicketId(ticketIdStr);
        try {
            parkingMeterService.payAFee(ticketId);
        } catch (UnknownTicketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FeeDTO getCurrentFee(String stopoverIdStr) {
        final TicketId stopoverId = new TicketId(stopoverIdStr);
        Fee fee = null;
        try {
            fee = parkingMeterService.getCurrentFee(stopoverId);
        } catch (UnknownTicketException e) {
            e.printStackTrace();
        }
        return new FeeDTO(fee);
    }
}
