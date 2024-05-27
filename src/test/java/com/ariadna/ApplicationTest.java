package com.ariadna;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ariadna.api.models.EventWithSourceResponse;
import com.ariadna.api.models.SourceResponse;
import com.ariadna.utils.DataFileLoader;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationContext context;

    @Before
    public void setup(){
        DataFileLoader fr = context.getBean(DataFileLoader.class);
        fr.loadFiles("source", "./src/test/resources/sources", false);
        fr.loadFiles("event", "./src/test/resources/events", false);
    }
    
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void loadEventsWithSource110() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/events/source/110"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();
         
        List<EventWithSourceResponse> eventsResponse = new ArrayList<EventWithSourceResponse>();
        eventsResponse.add(new EventWithSourceResponse(1, 110, new SourceResponse(110, "Capricorn"), 1708876517589L, -841));
        eventsResponse.add(new EventWithSourceResponse(7, 110, new SourceResponse(110, "Capricorn"), 1711752945679L, 424));

        Assert.assertEquals(response, objectMapper.writeValueAsString(eventsResponse));
    }

    @Test
    public void loadEventsBetweenValues100and500() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/events/min/100/max/500"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();
         
        List<EventWithSourceResponse> eventsResponse = new ArrayList<EventWithSourceResponse>();
        eventsResponse.add(new EventWithSourceResponse(7, 110, new SourceResponse(110, "Capricorn"), 1711752945679L, 424));
        eventsResponse.add(new EventWithSourceResponse(10, 106, new SourceResponse(106, "Virgo"), 1713185333182L, 240));
        
        Assert.assertEquals(response, objectMapper.writeValueAsString(eventsResponse));
    }
    
}
