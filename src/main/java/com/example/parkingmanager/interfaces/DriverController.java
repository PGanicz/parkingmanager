package com.example.parkingmanager.interfaces;

import com.example.parkingmanager.domain.model.ticket.UnknownTicketException;
import com.example.parkingmanager.interfaces.facade.ParkingMeterServiceFacade;
import com.example.parkingmanager.interfaces.facade.dto.FeeDTO;
import com.example.parkingmanager.interfaces.facade.dto.TicketDTO;
import com.example.parkingmanager.interfaces.shared.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
class DriverController {

    private ParkingMeterServiceFacade parkingMeterServiceFacade;

    @Autowired
    public void setParkingMeterServiceFacade(ParkingMeterServiceFacade parkingMeterServiceFacade) {
        this.parkingMeterServiceFacade = parkingMeterServiceFacade;
    }

    @RequestMapping(value = "/ticket", method = RequestMethod.POST)
    public @ResponseBody
    TicketDTO start(@RequestParam("DriverId") String driverIdStr,
                    @RequestParam("NumberPlate") String numberPlate) {
        return parkingMeterServiceFacade.createNewTicket(driverIdStr, numberPlate);
    }

    @RequestMapping(value = "/fee", method = RequestMethod.POST)
    public void payFee(@RequestParam("TicketId") String ticketId) throws UnknownTicketException {
        parkingMeterServiceFacade.payAFee(ticketId);
    }

    @RequestMapping(value = "/fee", method = RequestMethod.GET)
    public @ResponseBody
    FeeDTO getFee(@RequestParam("TicketId") String ticketId)  throws UnknownTicketException{
        return parkingMeterServiceFacade.getCurrentFee(ticketId);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UnknownTicketException.class})
    public @ResponseBody
    ApiError handleUnknownTicketException(UnknownTicketException ex) {
        return new ApiError(HttpStatus.BAD_REQUEST, "Ticket not exists.");
    }

}