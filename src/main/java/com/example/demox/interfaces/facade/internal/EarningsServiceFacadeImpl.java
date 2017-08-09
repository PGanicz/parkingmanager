package com.example.demox.interfaces.facade.internal;

import com.example.demox.application.EarningsService;
import com.example.demox.domain.model.Fee;
import com.example.demox.interfaces.facade.EarningsServiceFacade;
import com.example.demox.interfaces.facade.dto.FeeDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class EarningsServiceFacadeImpl implements EarningsServiceFacade {
    private EarningsService earningsService;

    @Autowired
    public void setEarningsService(EarningsService earningsService) {
        this.earningsService = earningsService;
    }

    @Override
    public FeeDTO getTotalEarningsForDay(Date date) {
        final Fee fee = earningsService.getTotalEarningsForDay(date);
        final FeeDTO feeDTO = new FeeDTO(fee.getFine().toString(), fee.getCurrency());

        return feeDTO;
    }
}
