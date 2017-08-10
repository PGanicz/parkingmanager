package com.example.demox.infrastructure;

import com.example.demox.domain.model.payment.Fee;
import com.example.demox.domain.model.payment.FeeRepository;

import java.util.Collection;
import java.util.Date;

public class FeeRepostioryImpl implements FeeRepository {
    @Override
    public void store(Fee fee) {

    }

    @Override
    public Collection<Fee> getFeesByDayOfYear(Date date) {
        return null;
    }
}
