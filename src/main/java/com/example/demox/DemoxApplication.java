package com.example.demox;

import com.example.demox.application.ParkingCheckerService;
import com.example.demox.application.impl.ParkingCheckerServiceImpl;
import com.example.demox.interfaces.facade.EarningsServiceFacade;
import com.example.demox.interfaces.facade.ParkingMeterServiceFacade;
import com.example.demox.interfaces.facade.internal.EarningsServiceFacadeImpl;
import com.example.demox.interfaces.facade.internal.ParkingMeterServiceFacadeImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoxApplication {

	@Bean
	ParkingMeterServiceFacade parkingMeterServiceFacade() {
		return new ParkingMeterServiceFacadeImpl();
	}
	@Bean
	EarningsServiceFacade earningsServiceFacade() {
		return new EarningsServiceFacadeImpl();
	}
	@Bean
	ParkingCheckerService parkingCheckerService() {
		return new ParkingCheckerServiceImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoxApplication.class, args);
	}
}
