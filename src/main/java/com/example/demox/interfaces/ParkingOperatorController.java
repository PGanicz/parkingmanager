package com.example.demox.interfaces;


import com.example.demox.application.ParkingCheckerService;
import com.example.demox.interfaces.facade.ParkingMeterServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingOperatorController {

    private ParkingCheckerService parkingCheckerService;

    @Autowired
    public void setParkingCheckerService(ParkingCheckerService parkingCheckerService) {
        this.parkingCheckerService = parkingCheckerService;
    }

    @RequestMapping(value = "/stopover/start" , method = RequestMethod.GET)
    public String checkIfParked() {
        return "false";
    }
}
