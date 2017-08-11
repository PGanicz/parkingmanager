package com.example.demox.interfaces;


import com.example.demox.DemoxApplication;
import com.example.demox.interfaces.shared.ApiError;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    public void ticketIdNotFound() throws Exception {
        MvcResult result = mockMvc.perform(get("/fee")
                .param("TicketId","SDSFDF"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        String message = JsonPath.read(content, "$.message");
    }

    @Test
    public void newStopover() throws Exception {
        mockMvc.perform(post("/stopover/start")
                .param("DriverId", "FDAFDASF"))
                .andExpect(status().isOk());
    }


    @Test
    public void newStartEnd() throws Exception {
        MvcResult result = mockMvc.perform(post("/stopover/start")
                .param("DriverId", "FDAFDASF"))
                .andExpect(status().isOk())
                .andReturn();

        String uuidStr = result.getResponse().getContentAsString();
        try {
            UUID uuid = UUID.fromString(uuidStr);
        } catch (IllegalArgumentException exception) {
            assertTrue("The exception has been rised: " +exception.getMessage(),false);
        }

        MvcResult result2 = mockMvc.perform(post("/stopover/end")
                .param("StopoverId", uuidStr))
                .andExpect(status().isOk())
                .andReturn();
    }
}
