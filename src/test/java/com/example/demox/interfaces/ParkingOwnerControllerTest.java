package com.example.demox.interfaces;

import com.example.demox.DemoxApplication;

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

import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoxApplication.class)
@WebAppConfiguration
public class ParkingOwnerControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void cannotCheckInvalidDate() throws Exception {
        mockMvc.perform(get("/earnings")
                .param("date","123"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status", Matchers.is("BAD_REQUEST")))
                .andExpect(jsonPath("$.message",
                        Matchers.is("Provided date param has other format than dd-MM-yyyy")));
    }
    @Test
    public void canCheckValidDate() throws Exception {
        mockMvc.perform(get("/earnings")
                .param("date","10-20-2017"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.value").isNumber())
                .andExpect(jsonPath("$.currency",
                        Matchers.is("PLN")));
    }
}
