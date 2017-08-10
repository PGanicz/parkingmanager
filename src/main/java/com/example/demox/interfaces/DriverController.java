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
    TicketDTO start(@RequestParam("DriverId") String driverIdStr) {
        return parkingMeterServiceFacade.createNewTicket(driverIdStr);
    }

    @RequestMapping(value = "/fee", method = RequestMethod.POST)
    public @ResponseBody
    String end(@RequestParam("StopoverId") String stopoverId) {
        parkingMeterServiceFacade.createNewTicket(stopoverId);
        return "Paid! Done";
    }

    @RequestMapping(value = "/fee", method = RequestMethod.GET)
    public String getFee(@RequestParam("StopoverId") String stopoverId) {
        return parkingMeterServiceFacade.getCurrentFee(stopoverId);
    }
}