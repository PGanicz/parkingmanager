package com.example.demox;

import com.example.demox.application.EarningsService;
import com.example.demox.application.ParkingCheckerService;
import com.example.demox.application.ParkMeterService;
import com.example.demox.application.impl.EarningsServiceImpl;
import com.example.demox.application.impl.ParkingCheckerServiceImpl;
import com.example.demox.application.impl.ParkMeterServiceImpl;
import com.example.demox.domain.model.clock.ClockService;
import com.example.demox.domain.model.driver.DriverRepository;
import com.example.demox.domain.model.fee.FeeRepository;
import com.example.demox.domain.model.ticket.TicketRepository;
import com.example.demox.infrastructure.ClockServiceImpl;
import com.example.demox.infrastructure.DriverRepositoryImpl;
import com.example.demox.infrastructure.FeeRepostioryImpl;
import com.example.demox.infrastructure.TicketRepositoryImpl;
import com.example.demox.interfaces.facade.EarningsServiceFacade;
import com.example.demox.interfaces.facade.ParkingCheckerServiceFacade;
import com.example.demox.interfaces.facade.ParkingMeterServiceFacade;
import com.example.demox.interfaces.facade.internal.EarningsServiceFacadeImpl;
import com.example.demox.interfaces.facade.internal.ParkingCheckerServiceFacadeImpl;
import com.example.demox.interfaces.facade.internal.ParkingMeterServiceFacadeImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoxApplication {
	@Bean
	ClockService clockService() {
		return new ClockServiceImpl();
	}
	@Bean
	TicketRepository stopoverRepository() {
		return new TicketRepositoryImpl();
	}

	@Bean
	DriverRepository driverRepository() {
		return new DriverRepositoryImpl();
	}
	@Bean
	FeeRepository feeRepository() {
		return new FeeRepostioryImpl();
	}
	@Bean
	EarningsService earningsService() {
		return new EarningsServiceImpl();

	}
	@Bean
	ParkingCheckerService parkingCheckerService() {
		return new ParkingCheckerServiceImpl();
	}
	@Bean
    ParkMeterService parkingMeterService() {
		return new ParkMeterServiceImpl();
	}

	@Bean
	ParkingMeterServiceFacade parkingMeterServiceFacade() {
		return new ParkingMeterServiceFacadeImpl();
	}
	@Bean
	EarningsServiceFacade earningsServiceFacade() {
		return new EarningsServiceFacadeImpl();
	}
	@Bean
	ParkingCheckerServiceFacade parkingCheckerServiceFacade() {
		return new ParkingCheckerServiceFacadeImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoxApplication.class, args);
	}
}
