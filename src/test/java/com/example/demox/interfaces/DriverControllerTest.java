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

import java.util.Date;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoxApplication.class)
@WebAppConfiguration
public class DriverControllerTest  {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    public TicketRepository ticketRepository;

    @MockBean
    public ClockService clockService;



    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void cannotCheckFeeForNonExistingTicket() throws Exception {
        mockMvc.perform(get("/fee")
                .param("TicketId","SDSFDF"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status", Matchers.is("BAD_REQUEST")))
                .andExpect(jsonPath("$.message", Matchers.is("Ticket not exists.")));
    }
    @Test
    public void cannotPayForNonExistingTicket() throws Exception {
        mockMvc.perform(post("/fee")
                .param("TicketId","SDSFDF"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createNewTicket() throws Exception {
        given(this.clockService.getCurrentDate())
                .willReturn(new Date());

        mockMvc.perform(post("/ticket")
                .param("DriverId", "FDAFDASF")
                .param("NumberPlate", "BP99 NPC"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.ticketId").exists())
                .andExpect(jsonPath("$.ticketId").isString())
                .andExpect(jsonPath("$.creationTime").exists())
                .andExpect(jsonPath("$.creationTime").isString());
    }


    @Test
    public void payAFee() throws Exception {
        final TicketId ticketId = ticketRepository.nextTicketId();
        final NumberPlate numberPlate = new NumberPlate("BP99 NPC");
        final DriverId driverId = new DriverId("AFSAF");
        final Ticket ticket = new Ticket(ticketId, numberPlate, driverId, new Date());
        ticketRepository.store(ticket);

        given(this.clockService.getCurrentDate()).willReturn(new Date());

        mockMvc.perform(post("/fee")
                .param("TicketId",ticketId.toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void getFee() throws Exception {
        final Date creationTime = new Date();
        final Date paymentTime = new Date(creationTime.getTime()+3600);

        given(this.clockService.getCurrentDate())
                .willReturn(creationTime)
                .willReturn(paymentTime);

        final TicketId ticketId = ticketRepository.nextTicketId();
        final NumberPlate numberPlate = new NumberPlate("BP99 NPC");
        final DriverId driverId = new DriverId("AFSAF");
        final Ticket ticket = new Ticket(ticketId, numberPlate, driverId, creationTime);
        ticketRepository.store(ticket);

        mockMvc.perform(get("/fee")
                .param("TicketId", ticketId.toString()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.value").exists())
                .andExpect(jsonPath("$.currency", Matchers.is("PLN")))
                .andExpect(status().isOk());


    }



}
