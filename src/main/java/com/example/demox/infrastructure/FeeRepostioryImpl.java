package com.example.demox.infrastructure;

import com.example.demox.domain.model.fee.Fee;
import com.example.demox.domain.model.fee.FeeRepository;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FeeRepostioryImpl implements FeeRepository {
    public List<Fee> db = new ArrayList<>();
    @Override
    public void store(Fee fee) {
        db.add(fee);
    }

    @Override
    public Collection<Fee> getFeesByDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());

        return db.stream().filter(new Predicate<Fee>() {
            @Override
            public boolean test(Fee fee) {
                final Calendar cal2 = Calendar.getInstance();
                cal2.setTimeInMillis(fee.getPaymentTime().getTime());
                if (cal.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                        cal.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
                    return true;
                }
                return false;
            }
        }).collect(Collectors.toList());
    }
}
