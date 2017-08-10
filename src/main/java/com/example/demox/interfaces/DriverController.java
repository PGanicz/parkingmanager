package com.example.demox.interfaces;

import com.example.demox.interfaces.facade.ParkingMeterServiceFacade;
import com.example.demox.interfaces.facade.internal.ParkingMeterServiceFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DriverController {

    private ParkingMeterServiceFacade parkingMeterServiceFacade;

    @Autowired
    public void setParkingMeterServiceFacade(ParkingMeterServiceFacade parkingMeterServiceFacade) {
        this.parkingMeterServiceFacade = parkingMeterServiceFacade;
    }

    @RequestMapping(value = "/stopover/start", method = RequestMethod.POST)
    public String start(@RequestParam("VehicleId") String vehicleId,
                        @RequestParam("NumberPlate") String numberPlate) {
        return parkingMeterServiceFacade.registerNewStopover(vehicleId, numberPlate);
    }

    @RequestMapping(value = "/stopover/end", method = RequestMethod.POST)
    public String end(@RequestParam("StopoverId") String stopoverId) {
        parkingMeterServiceFacade.registerEndOfStopover(stopoverId);
        return "Paid! Done";
    }

    @RequestMapping(value = "/stopover/fee", method = RequestMethod.GET)
    public String getFee(@RequestParam("StopoverId") String stopoverId) {
        return parkingMeterServiceFacade.getCurrentFee(stopoverId);
    }

}
