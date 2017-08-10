package com.example.demox.domain.model.payment;

import java.util.Collection;
import java.util.Date;

public interface FeeRepository  {
    void store(Fee fee);
    Collection<Fee> getFeesByDayOfYear(Date date);
}
