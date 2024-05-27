package com.ariadna;

import static org.mockito.ArgumentMatchers.anyLong;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ariadna.api.controllers.EventsController;
import com.ariadna.api.models.EventWithSourceResponse;
import com.ariadna.api.models.SourceResponse;
import com.ariadna.domain.models.EventWithSource;
import com.ariadna.domain.models.Source;
import com.ariadna.domain.services.EventsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EventsController.class)
@RunWith(SpringRunner.class)
public class EventsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventsService eventsService;
    
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void loadEventsWithSource101() throws Exception {
        List<EventWithSource> events = new ArrayList<EventWithSource>();
        events.add(new EventWithSource(1, 101, new Source(101, "Pisces"), 1, 1));
        events.add(new EventWithSource(2, 101, new Source(101, "Pisces"), 2, 2));
        events.add(new EventWithSource(3, 101, new Source(101, "Pisces"), 3, 3));
        
        Mockito.when(eventsService.getEventsBySource(anyLong()))
         .thenReturn(events);

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/events/source/101"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();
         
        List<EventWithSourceResponse> eventsResponse = new ArrayList<EventWithSourceResponse>();
        eventsResponse.add(new EventWithSourceResponse(1, 101, new SourceResponse(101, "Pisces"), 1, 1));
        eventsResponse.add(new EventWithSourceResponse(2, 101, new SourceResponse(101, "Pisces"), 2, 2));
        eventsResponse.add(new EventWithSourceResponse(3, 101, new SourceResponse(101, "Pisces"), 3, 3));

        Assert.assertEquals(response, objectMapper.writeValueAsString(eventsResponse));
    }

    
    @Test
    public void loadEventsWithSource207() throws Exception {
        List<EventWithSource> events = new ArrayList<EventWithSource>();
        events.add(new EventWithSource(1, 207, new Source(207, "Rooster"), 1, 1));
        events.add(new EventWithSource(2, 207, new Source(207, "Rooster"), 2, 2));
        events.add(new EventWithSource(3, 207, new Source(207, "Rooster"), 3, 3));

        Mockito.when(eventsService.getEventsBySource(anyLong()))
         .thenReturn(events);

        EventsService eventsService = Mockito.mock(EventsService.class);	
        Mockito.when(eventsService.getEventsBySource(207))
        .thenReturn(events);

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/events/source/207"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        List<EventWithSourceResponse> eventsResponse = new ArrayList<EventWithSourceResponse>();
        eventsResponse.add(new EventWithSourceResponse(1, 207, new SourceResponse(207, "Rooster"), 1, 1));
        eventsResponse.add(new EventWithSourceResponse(2, 207, new SourceResponse(207, "Rooster"), 2, 2));
        eventsResponse.add(new EventWithSourceResponse(3, 207, new SourceResponse(207, "Rooster"), 3, 3));

        Assert.assertEquals(response, objectMapper.writeValueAsString(eventsResponse));
    }

    
    @Test
    public void loadEventsWithTimestamps1and20() throws Exception {
        List<EventWithSource> events = new ArrayList<EventWithSource>();
        events.add(new EventWithSource(1, 101, new Source(101, "Pisces"), 5, 1));
        events.add(new EventWithSource(2, 202, new Source(202, "Ox"), 10, 2));
        events.add(new EventWithSource(3, 303, new Source(303, "Wood"), 15, 3));

        Mockito.when(eventsService.getEventsByTimeStamps(anyLong(), anyLong()))
         .thenReturn(events);

        List<EventWithSourceResponse> eventsResponse = new ArrayList<EventWithSourceResponse>();
        eventsResponse.add(new EventWithSourceResponse(1, 101, new SourceResponse(101, "Pisces"), 5, 1));
        eventsResponse.add(new EventWithSourceResponse(2, 202, new SourceResponse(202, "Ox"), 10, 2));
        eventsResponse.add(new EventWithSourceResponse(3, 303, new SourceResponse(303, "Wood"), 15, 3));

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/events/start/1/end/20"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        Assert.assertEquals(response, objectMapper.writeValueAsString(eventsResponse));
    }

    @Test
    public void loadEventsWithTimestamps4and16() throws Exception {
        List<EventWithSource> events = new ArrayList<EventWithSource>();
        events.add(new EventWithSource(1, 101, new Source(101, "Pisces"), 5, -1));
        events.add(new EventWithSource(2, 202, new Source(202, "Ox"), 10, 2));
        events.add(new EventWithSource(3, 303, new Source(303, "Wood"), 15, 3));

        Mockito.when(eventsService.getEventsByTimeStamps(anyLong(), anyLong()))
        .thenReturn(events);
        
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/events/start/4/end/16"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();
        
        List<EventWithSourceResponse> eventsResponse = new ArrayList<EventWithSourceResponse>();
        eventsResponse.add(new EventWithSourceResponse(1, 101, new SourceResponse(101, "Pisces"), 5, -1));
        eventsResponse.add(new EventWithSourceResponse(2, 202, new SourceResponse(202, "Ox"), 10, 2));
        eventsResponse.add(new EventWithSourceResponse(3, 303, new SourceResponse(303, "Wood"), 15, 3));

        Assert.assertEquals(response, objectMapper.writeValueAsString(eventsResponse));
    }

    
    @Test
    public void loadEventsBetweenValues1and10() throws Exception {
        List<EventWithSource> events = new ArrayList<EventWithSource>();
        events.add(new EventWithSource(1, 101, new Source(101, "Pisces"), 5, 1));
        events.add(new EventWithSource(2, 202, new Source(202, "Ox"), 10, 2));
        events.add(new EventWithSource(3, 303, new Source(303, "Wood"), 15, 3));

        Mockito.when(eventsService.getEventsByValues(anyLong(), anyLong()))
        .thenReturn(events);
 
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/events/min/1/max/10"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();
        
        List<EventWithSourceResponse> eventsResponse = new ArrayList<EventWithSourceResponse>();
        eventsResponse.add(new EventWithSourceResponse(1, 101, new SourceResponse(101, "Pisces"), 5, 1));
        eventsResponse.add(new EventWithSourceResponse(2, 202, new SourceResponse(202, "Ox"), 10, 2));
        eventsResponse.add(new EventWithSourceResponse(3, 303, new SourceResponse(303, "Wood"), 15, 3));

        Assert.assertEquals(response, objectMapper.writeValueAsString(eventsResponse));
    }

    @Test
    public void loadEventsBetweenValuesMinus5and5() throws Exception {
        List<EventWithSource> events = new ArrayList<EventWithSource>();
        events.add(new EventWithSource(1, 101, new Source(101, "Pisces"), 5, 1));
        events.add(new EventWithSource(2, 202, new Source(202, "Ox"), 10, 2));
        events.add(new EventWithSource(3, 303, new Source(303, "Wood"), 15, 3));
        
        Mockito.when(eventsService.getEventsByValues(anyLong(), anyLong()))
        .thenReturn(events);
        
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/events/min/-5/max/5"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

        List<EventWithSourceResponse> eventsResponse = new ArrayList<EventWithSourceResponse>();
        eventsResponse.add(new EventWithSourceResponse(1, 101, new SourceResponse(101, "Pisces"), 5, 1));
        eventsResponse.add(new EventWithSourceResponse(2, 202, new SourceResponse(202, "Ox"), 10, 2));
        eventsResponse.add(new EventWithSourceResponse(3, 303, new SourceResponse(303, "Wood"), 15, 3));

        Assert.assertEquals(response, objectMapper.writeValueAsString(eventsResponse));
    }
}
