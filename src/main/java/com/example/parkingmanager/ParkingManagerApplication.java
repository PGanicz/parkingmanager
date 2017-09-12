package com.example.parkingmanager;

import com.example.parkingmanager.application.EarningsService;
import com.example.parkingmanager.application.ParkingCheckerService;
import com.example.parkingmanager.application.ParkMeterService;
import com.example.parkingmanager.application.impl.EarningsServiceImpl;
import com.example.parkingmanager.application.impl.ParkingCheckerServiceImpl;
import com.example.parkingmanager.application.impl.ParkMeterServiceImpl;
import com.example.parkingmanager.domain.model.clock.ClockService;
import com.example.parkingmanager.domain.model.driver.DriverRepository;
import com.example.parkingmanager.domain.model.fee.FeeCalculationService;
import com.example.parkingmanager.domain.model.fee.FeeRepository;
import com.example.parkingmanager.domain.model.ticket.TicketRepository;
import com.example.parkingmanager.infrastructure.ClockServiceImpl;
import com.example.parkingmanager.infrastructure.DriverRepositoryImpl;
import com.example.parkingmanager.infrastructure.FeeRepostioryImpl;
import com.example.parkingmanager.infrastructure.TicketRepositoryImpl;
import com.example.parkingmanager.interfaces.facade.EarningsServiceFacade;
import com.example.parkingmanager.interfaces.facade.ParkingCheckerServiceFacade;
import com.example.parkingmanager.interfaces.facade.ParkingMeterServiceFacade;
import com.example.parkingmanager.interfaces.facade.internal.EarningsServiceFacadeImpl;
import com.example.parkingmanager.interfaces.facade.internal.ParkingCheckerServiceFacadeImpl;
import com.example.parkingmanager.interfaces.facade.internal.ParkingMeterServiceFacadeImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ParkingManagerApplication {
	@Bean
	FeeCalculationService feeCalculationService() {
		return new FeeCalculationService();
	}
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
		SpringApplication.run(ParkingManagerApplication.class, args);
	}
}
