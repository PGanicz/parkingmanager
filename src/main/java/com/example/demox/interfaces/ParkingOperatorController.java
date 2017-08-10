package com.example.demox.interfaces;


import com.example.demox.application.ParkingCheckerService;
import com.example.demox.interfaces.facade.ParkingCheckerServiceFacade;
import com.example.demox.interfaces.facade.ParkingMeterServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingOperatorController {

    private ParkingCheckerServiceFacade parkingCheckerServiceFacade;

    @Autowired
    public void setParkingCheckerServiceFacade(ParkingCheckerServiceFacade parkingCheckerServiceFacade) {
        this.parkingCheckerServiceFacade = parkingCheckerServiceFacade;
    }

    @RequestMapping(value = "/stopover/start" , method = RequestMethod.GET)
    public String checkIfParked(@RequestParam("StopoverId") String stopoverId) {
        return Boolean.toString(parkingCheckerServiceFacade.check(stopoverId));
    }
}
