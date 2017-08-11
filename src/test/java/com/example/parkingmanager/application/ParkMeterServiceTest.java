package com.example.parkingmanager.application;

import com.example.parkingmanager.application.impl.ParkMeterServiceImpl;
import com.example.parkingmanager.domain.model.clock.ClockService;
import com.example.parkingmanager.domain.model.driver.Driver;
import com.example.parkingmanager.domain.model.driver.DriverId;
import com.example.parkingmanager.domain.model.driver.DriverRepository;
import com.example.parkingmanager.domain.model.fee.Fee;
import com.example.parkingmanager.domain.model.fee.FeeCalculationService;
import com.example.parkingmanager.domain.model.fee.FeeRepository;
import com.example.parkingmanager.domain.model.ticket.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;


import java.math.BigDecimal;
import java.util.Date;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;



@RunWith(PowerMockRunner.class)
public class ParkMeterServiceTest {
    private ParkMeterServiceImpl parkMeterService;

    private TicketRepository ticketRepository;
    private DriverRepository driverRepository;
    private FeeRepository feeRepository;
    private ClockService clockService;

    private  DriverId driverId;
    private  NumberPlate numberPlate;
    private  TicketId ticketId;
    private  Date creationDate;
    private  Ticket ticket;
    private  Driver vipDriver;
    private  Fee fee;


    @Before
    public void setUp() throws Exception {
        parkMeterService = new ParkMeterServiceImpl();
        driverId = new DriverId("ABC");
        numberPlate = new NumberPlate("ABC");
        ticketId = new TicketId("ticket_id");
        creationDate = new Date();
        ticket = new Ticket(ticketId, numberPlate, driverId, creationDate);
        vipDriver = new Driver(driverId, Driver.Type.VIP);
        fee = new Fee(new BigDecimal(1), "PLN");

        ticketRepository = mock(TicketRepository.class);
        driverRepository = mock(DriverRepository.class);
        feeRepository = mock(FeeRepository.class);
        clockService = mock(ClockService.class);

        parkMeterService.setClockService(clockService);
        parkMeterService.setDriverRepository(driverRepository);
        parkMeterService.setFeeRepository(feeRepository);
        parkMeterService.setTicketRepository(ticketRepository);
        parkMeterService.setFeeCalculationService(new FeeCalculationService());
    }

    @Test
    public void testCreateNewTicket() {
        when(ticketRepository.nextTicketId()).thenReturn(ticketId);
        when(clockService.getCurrentDate()).thenReturn(creationDate);

        final Ticket result = parkMeterService.createNewTicket(driverId, numberPlate);

        assertEquals(result.getDriverId(),driverId);
        assertEquals(result.getTicketId(), ticketId);
        assertEquals(result.getNumberPlate(), numberPlate);
        assertEquals(result.getCreationDate(), creationDate);

        verify(ticketRepository, times(1)).store(result);
    }
    @Test
    public void testPayFeeForNotExistingTicket() {

        when(clockService.getCurrentDate()).thenReturn(creationDate);
        when(ticketRepository.findById(ticketId)).thenReturn(null);

        try {
            parkMeterService.payAFee(ticketId);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UnknownTicketException.class)
                    .hasMessage("No ticket with id " + ticketId.toString() + " exists in the system");
        }
    }
    @Test
    public void testPayAFeeForTicket() {

        when(clockService.getCurrentDate()).thenReturn(creationDate);
        when(ticketRepository.findById(ticketId)).thenReturn(ticket);
        when(driverRepository.find(driverId)).thenReturn(vipDriver);

        try {
            parkMeterService.payAFee(ticketId);
        } catch (UnknownTicketException e) {
            e.printStackTrace();
        }

        verify(feeRepository,times(1))
                .store(fee);
        verify(ticketRepository, times(1))
                .delete(ticket);
    }
    @Test
    public void testGetFeeOfNotExistingTicket() {
        mockStatic(FeeCalculationService.class);
        when(clockService.getCurrentDate()).thenReturn(creationDate);
        when(ticketRepository.findById(ticketId)).thenReturn(null);


        Exception exception = null;
        try {
            Fee fee = parkMeterService.getCurrentFee(ticketId);
        } catch (Exception e) {
            exception = e;
        }
        assertThat(exception).isInstanceOf(UnknownTicketException.class)
                .hasMessage("No ticket with id " + ticketId.toString() + " exists in the system");
    }

    @Test
    public void testCanGetCurrentFee() {
        when(clockService.getCurrentDate()).thenReturn(creationDate);
        when(ticketRepository.findById(ticketId)).thenReturn(ticket);
        Exception exception = null;
        Fee fee = null;
        try {
            fee = parkMeterService.getCurrentFee(ticketId);
        } catch (Exception e) {
            exception = e;
        }
        assertTrue(fee != null);
    }
}
