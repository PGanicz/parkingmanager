package com.example.demox.interfaces;

import com.example.demox.application.EarningsService;
import com.example.demox.interfaces.facade.EarningsServiceFacade;
import com.example.demox.interfaces.facade.dto.FeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
public class ParkingOwnerController {

    private EarningsServiceFacade earningsServiceFacade;

    @Autowired
    public void setEarningsServiceFacade(EarningsServiceFacade earningsServiceFacade) {
        this.earningsServiceFacade = earningsServiceFacade;
    }

    @RequestMapping(value = "/earnings", method = RequestMethod.GET)
    public @ResponseBody
    FeeDTO getEarningsForDay(@RequestParam("date") String day) throws ParseException {
        return earningsServiceFacade.getTotalEarningsForDay(day);
    }
}
