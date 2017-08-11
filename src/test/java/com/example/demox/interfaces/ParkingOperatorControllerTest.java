package com.example.demox.interfaces;
import com.example.demox.DemoxApplication;


import com.example.demox.domain.model.clock.ClockService;
import com.example.demox.domain.model.driver.DriverId;
import com.example.demox.domain.model.ticket.NumberPlate;
import com.example.demox.domain.model.ticket.Ticket;
import com.example.demox.domain.model.ticket.TicketId;
import com.example.demox.domain.model.ticket.TicketRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoxApplication.class)
@WebAppConfiguration
public class ParkingOperatorControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @MockBean
    public TicketRepository ticketRepository;
    @Autowired
    public ClockService clockService;

    @Test
    public void checkNotRegisteredVehicle() throws Exception {
        given(this.ticketRepository.findAll())
                .willReturn(new ArrayList<>());

        mockMvc.perform(get("/state")
                .param("NumberPlate","WrongNumber"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.state", Matchers.is("Unknown vehicle.")));
    }

    @Test
    public void checkRegisteredVehicle() throws Exception {

        final List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(new TicketId("ticketid"),
                               new NumberPlate("ValidNumber"),
                                new DriverId("driverid"),
                                new Date()));

        given(this.ticketRepository.findAll())
                .willReturn(tickets);

        mockMvc.perform(get("/state")
                .param("NumberPlate","ValidNumber"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.state", Matchers.is("That vehicle has been registered.")));

    }
}
