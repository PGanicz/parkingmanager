package com.example.demox;

import com.example.demox.application.EarningsService;
import com.example.demox.application.ParkingCheckerService;
import com.example.demox.application.ParkingMeterService;
import com.example.demox.application.impl.EarningsServiceImpl;
import com.example.demox.application.impl.ParkingCheckerServiceImpl;
import com.example.demox.application.impl.ParkingMeterServiceImpl;
import com.example.demox.domain.model.driver.DriverRepository;
import com.example.demox.domain.model.payment.FeeRepository;
import com.example.demox.domain.model.stepover.StopoverRepository;
import com.example.demox.infrastructure.DriverRepositoryImpl;
import com.example.demox.infrastructure.FeeRepostioryImpl;
import com.example.demox.infrastructure.StopoverRepositoryImpl;
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
	StopoverRepository stopoverRepository() {
		return new StopoverRepositoryImpl();
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
	ParkingMeterService parkingMeterService() {
		return new ParkingMeterServiceImpl();
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
