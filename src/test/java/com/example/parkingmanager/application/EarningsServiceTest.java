package com.example.parkingmanager.application;

import com.example.parkingmanager.application.impl.EarningsServiceImpl;
import com.example.parkingmanager.domain.model.fee.Fee;
import com.example.parkingmanager.domain.model.fee.FeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EarningsServiceTest {

    private FeeRepository feeRepository;
    private EarningsServiceImpl earningsService;
    @Before
    public void setUp() throws Exception {
        feeRepository = mock(FeeRepository.class);
        earningsService = new EarningsServiceImpl();
        earningsService.setFeeRepository(feeRepository);
    }

    @Test
    public void getEarnignsFromClearDatabase() {
        final Date currDate = new Date();
        when(feeRepository.getFeesByDayOfYear(currDate)).thenReturn(new ArrayList<>());

        Fee fee = earningsService.getTotalEarningsForDay(currDate);

        assertTrue(fee.getFine().equals(new BigDecimal("0")));
    }

    @Test
    public void getEarningsFromDatabase() {
        final Date currDate = new Date();
        ArrayList<Fee> fees = new ArrayList<>();
        fees.add(new Fee(new BigDecimal(2.23), "PLN"));
        fees.add(new Fee(new BigDecimal(2.43), "PLN"));
        fees.add(new Fee(new BigDecimal(5.1), "PLN"));

        when(feeRepository.getFeesByDayOfYear(currDate)).thenReturn(fees);

        Fee fee = earningsService.getTotalEarningsForDay(currDate);

        assertEquals(new BigDecimal("9.76"),
                fee.getFine().round(new MathContext(3, RoundingMode.HALF_UP)));
    }
}
