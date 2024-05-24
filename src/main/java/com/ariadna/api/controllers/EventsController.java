package com.ariadna.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ariadna.api.models.EventWithSourceResponse;
import com.ariadna.api.models.SourceResponse;
import com.ariadna.domain.models.EventWithSource;
import com.ariadna.domain.models.Source;
import com.ariadna.domain.services.EventsService;

@RestController
public class EventsController {
    private final EventsService eventsService;

    @Autowired
    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @RequestMapping(value = "/events/source/{source_id}", method = RequestMethod.GET)
    public List<EventWithSourceResponse> getEventsBySource(@PathVariable long source_id) {        
        List<EventWithSource> events = eventsService.getEventsBySource(source_id);        
        return events.stream().map(event -> mapToEventWithSourceResponse(event)).toList();
    }

    @RequestMapping(value = "/events/start/{startDate}/end/{endDate}", method = RequestMethod.GET) 
    public List<EventWithSourceResponse> getEventsByTimeStamps(@PathVariable(name = "startDate") long startDate, @PathVariable(name = "endDate") long endDate) {
        List<EventWithSource> events = eventsService.getEventsByTimeStamps(startDate, endDate);        
        return events.stream().map(event -> mapToEventWithSourceResponse(event)).toList();
    }

    @RequestMapping(value = "/events/min/{minValue}/max/{maxValue}", method = RequestMethod.GET) 
    public List<EventWithSourceResponse> getEventsByValues(@PathVariable(name = "minValue") long minValue, @PathVariable(name = "maxValue") long maxValue) {
        List<EventWithSource> events = eventsService.getEventsByValues(minValue, maxValue);
        return events.stream().map(event -> mapToEventWithSourceResponse(event)).toList();
    }

    private EventWithSourceResponse mapToEventWithSourceResponse(EventWithSource event) {
        SourceResponse source = mapToSourceResponse(event.getSource());
        return new EventWithSourceResponse(event.getId(), event.getSourceId(), source, event.getTimestamp(), event.getValue());
    }
    private SourceResponse mapToSourceResponse(Source source) {
        return new SourceResponse(source.getId(), source.getName());
    }
}
