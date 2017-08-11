package com.example.demox.interfaces;


import com.example.demox.DemoxApplication;

import com.jayway.jsonpath.JsonPath;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;


import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void checkFeeForNonExistingTicket() throws Exception {
        mockMvc.perform(get("/fee")
                .param("TicketId","SDSFDF"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status", Matchers.is("BAD_REQUEST")))
                .andExpect(jsonPath("$.message", Matchers.is("Ticket not exists.")));
    }
    @Test
    public void payForNonExistingTicket() throws Exception {
        mockMvc.perform(post("/fee")
                .param("TicketId","SDSFDF"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createNewTicket() throws Exception {
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
    public void getTicketAndPayFee() throws Exception {
        MvcResult result = mockMvc.perform(post("/ticket")
                .param("DriverId", "FDAFDASF")
                .param("NumberPlate", "BP99 NPC"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.ticketId").exists())
                .andExpect(jsonPath("$.ticketId").isString())
                .andExpect(jsonPath("$.creationTime").exists())
                .andExpect(jsonPath("$.creationTime").isString())
                .andReturn();
        String ticketId = JsonPath.read(result.getResponse().getContentAsString(),"$.ticketId");

        mockMvc.perform(get("/fee")
                .param("TicketId",ticketId))
                .andExpect(status().isOk());
    }

}
