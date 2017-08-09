package com.example.demox.interfaces;

import com.example.demox.application.EarningsService;
import com.example.demox.interfaces.facade.EarningsServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingOwnerController {

    private EarningsServiceFacade earningsServiceFacade;

    @Autowired
    public void setEarningsServiceFacade(EarningsServiceFacade earningsServiceFacade) {
        this.earningsServiceFacade = earningsServiceFacade;
    }

    @RequestMapping(value = "/earnings/{day}", method = RequestMethod.GET)
    public String earnings() {
        return "Nothing";
    }
}
