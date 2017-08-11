package com.example.demox;

import com.example.demox.application.EarningsService;
import com.example.demox.application.ParkingCheckerService;
import com.example.demox.application.ParkingMeterService;
import com.example.demox.application.impl.EarningsServiceImpl;
import com.example.demox.application.impl.ParkingCheckerServiceImpl;
import com.example.demox.application.impl.ParkingMeterServiceImpl;
import com.example.demox.domain.model.clock.ClockService;
import com.example.demox.domain.model.driver.DriverRepository;
import com.example.demox.domain.model.fee.FeeRepository;
import com.example.demox.domain.model.ticket.TicketRepository;
import com.example.demox.infrastructure.*;
import com.example.demox.interfaces.facade.EarningsServiceFacade;
import com.example.demox.interfaces.facade.ParkingCheckerServiceFacade;
import com.example.demox.interfaces.facade.ParkingMeterServiceFacade;
import com.example.demox.interfaces.facade.internal.EarningsServiceFacadeImpl;
import com.example.demox.interfaces.facade.internal.ParkingCheckerServiceFacadeImpl;
import com.example.demox.interfaces.facade.internal.ParkingMeterServiceFacadeImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoxApplicationTests {


	@Test
	public void contextLoads() {
	}

}
