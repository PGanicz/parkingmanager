package com.example.parkingmanager.application;

import com.example.parkingmanager.application.impl.ParkingCheckerServiceImpl;
import com.example.parkingmanager.domain.model.driver.DriverId;
import com.example.parkingmanager.domain.model.ticket.NumberPlate;
import com.example.parkingmanager.domain.model.ticket.Ticket;
import com.example.parkingmanager.domain.model.ticket.TicketId;
import com.example.parkingmanager.domain.model.ticket.TicketRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParkingCheckerServiceTest {

    private TicketRepository ticketRepository;
    private ParkingCheckerServiceImpl parkingCheckerService;
    @Before
    public void setUp() throws Exception {
        ticketRepository = mock(TicketRepository.class);
        parkingCheckerService = new ParkingCheckerServiceImpl();
        parkingCheckerService.setTicketRepository(ticketRepository);
    }
    @Test
    public void testVehicleHasNotTicket() {
        when(ticketRepository.findAll()).thenReturn(new ArrayList<>());

        assertFalse(parkingCheckerService.hasTicket(new NumberPlate("NumberPlate")));
    }

    @Test
    public void testVehicleIdHasTicket() {
        final NumberPlate numberPlate = new NumberPlate("Valid");
        final ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(new TicketId("ticket_id"), numberPlate, new DriverId("fad"), new Date()));
        when(ticketRepository.findAll()).thenReturn(tickets);

        assertTrue(parkingCheckerService.hasTicket(numberPlate));
    }
}
