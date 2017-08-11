package com.example.parkingmanager.application.impl;

import com.example.parkingmanager.application.EarningsService;
import com.example.parkingmanager.domain.model.fee.Fee;
import com.example.parkingmanager.domain.model.fee.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class EarningsServiceImpl implements EarningsService {

    private FeeRepository feeRepository;

    @Autowired
    public void setFeeRepository(FeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }

    @Override
    public Fee getTotalEarningsForDay(Date date) {
        Collection<Fee> collection = feeRepository.getFeesByDayOfYear(date);
        BigDecimal total = collection
                .stream()
                .map(Fee::getFine)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Fee(total, "PLN");
    }
}
