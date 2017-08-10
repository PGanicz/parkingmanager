package com.example.demox.application.impl;

import com.example.demox.application.EarningsService;
import com.example.demox.domain.model.payment.Fee;
import com.example.demox.domain.model.payment.FeeRepository;
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
        long totalEarnings = collection.stream()
                .mapToLong(fee -> fee.getFine().longValue())
                .sum();
        return new Fee(new BigDecimal(totalEarnings), "PLN");
    }
}
