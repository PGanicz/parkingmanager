package com.example.parkingmanager.interfaces;


import com.example.parkingmanager.interfaces.facade.ParkingCheckerServiceFacade;
import com.example.parkingmanager.interfaces.facade.dto.StateDTO;
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

    @RequestMapping(value = "/state" , method = RequestMethod.GET)
    public StateDTO getStateForVehicle(@RequestParam("NumberPlate") String numberPlate) {
        return parkingCheckerServiceFacade.getState(numberPlate);
    }
}
