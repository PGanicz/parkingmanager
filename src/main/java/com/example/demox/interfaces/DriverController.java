package com.example.demox.interfaces;

import com.example.demox.interfaces.facade.ParkingMeterServiceFacade;
import com.example.demox.interfaces.facade.internal.ParkingMeterServiceFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DriverController {

    private ParkingMeterServiceFacade parkingMeterServiceFacade;

    @Autowired
    public void setParkingMeterServiceFacade(ParkingMeterServiceFacade parkingMeterServiceFacade) {
        this.parkingMeterServiceFacade = parkingMeterServiceFacade;
    }

    @RequestMapping(value = "/stopover/start", method = RequestMethod.POST)
    public String start() {

        return "Started!";
    }

    @RequestMapping(value = "/stopover/end", method = RequestMethod.POST)
    public String end() {
        return "End!";
    }

}
