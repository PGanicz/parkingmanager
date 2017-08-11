package com.example.demox.interfaces;

import com.example.demox.interfaces.facade.EarningsServiceFacade;
import com.example.demox.interfaces.facade.dto.FeeDTO;
import com.example.demox.interfaces.shared.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler({ParseException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ApiError handleParseException(ParseException ex) {
        return  new ApiError(HttpStatus.BAD_REQUEST, "Provided date param has other format than dd-MM-yyyy");
    }
}
