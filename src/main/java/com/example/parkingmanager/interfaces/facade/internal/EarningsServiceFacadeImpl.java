package com.example.parkingmanager.interfaces.facade.internal;

import com.example.parkingmanager.application.EarningsService;
import com.example.parkingmanager.domain.model.fee.Fee;
import com.example.parkingmanager.interfaces.facade.EarningsServiceFacade;
import com.example.parkingmanager.interfaces.facade.dto.FeeDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EarningsServiceFacadeImpl implements EarningsServiceFacade {
    private EarningsService earningsService;

    @Autowired
    public void setEarningsService(EarningsService earningsService) {
        this.earningsService = earningsService;
    }

    @Override
    public FeeDTO getTotalEarningsForDay(String date) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
        Date dayDate = parser.parse(date);
        final Fee fee = earningsService.getTotalEarningsForDay(dayDate);
        return new FeeDTO(fee);
    }
}
