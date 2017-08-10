package com.example.demox.interfaces;

import com.example.demox.interfaces.facade.ParkingMeterServiceFacade;
import com.example.demox.interfaces.facade.dto.TicketDTO;
import com.example.demox.interfaces.facade.internal.ParkingMeterServiceFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public @ResponseBody
    String end(@RequestParam("TicketId") String ticketId) {
        parkingMeterServiceFacade.payAFee(ticketId);
        return "Paid! Done";
    }

    @RequestMapping(value = "/fee", method = RequestMethod.GET)
    public String getFee(@RequestParam("StopoverId") String stopoverId) {
        return parkingMeterServiceFacade.getCurrentFee(stopoverId);
    }
}