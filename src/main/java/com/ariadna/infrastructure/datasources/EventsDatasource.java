package com.ariadna.infrastructure.datasources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ariadna.domain.repositories.EventsRepository;
import com.ariadna.infrastructure.models.EventModel;
import com.ariadna.infrastructure.models.EventWithSourceModel;
import com.ariadna.infrastructure.models.SourceModel;

@Repository
public class EventsDatasource implements EventsRepository{
    private List<EventModel> events = new ArrayList<EventModel>();
    private List<SourceModel> sources = new ArrayList<SourceModel>();
    
    @Override
    public Optional<EventModel> getEventById(long id) {
        return events.stream().filter(event -> event.getId() == id).findFirst();
    }
    
    @Override
    public List<EventModel> getEventsByTimeStamps(long startDate, long endDate) {
        List<EventModel> foundEvents = events.stream().filter(event -> event.getTimestamp() >= startDate && event.getTimestamp() < endDate).toList();
        return foundEvents;
    }
    
    @Override
    public List<EventWithSourceModel> getEventsWithSourceByTimeStamps(long startDate, long endDate) {
        List<EventModel> events = getEventsByTimeStamps(startDate, endDate);
        return joinEventsAndSources(events);
    }
    
    @Override
    public List<EventModel> getEventsByValues(long minValue, long maxValue) {
        List<EventModel> foundEvents = events.stream().filter(event -> event.getValue() >= minValue && event.getValue() < maxValue).toList();
        return foundEvents;
    }
    
    @Override
    public List<EventWithSourceModel> getEventsWithSourceByValues(long minValue, long maxValue) {
        List<EventModel> events = getEventsByValues(minValue, maxValue);
        return joinEventsAndSources(events);
    }
    
    @Override
    public List<EventModel> getEventsBySourceId(long source_id) {
        List<EventModel> foundEvents = events.stream().filter(event -> event.getSourceId() == source_id).toList();
        return foundEvents;
    }

    @Override
    public List<EventWithSourceModel> getEventsWithSourceBySourceId(long source_id) {
        List<EventModel> events = getEventsBySourceId(source_id);
        return joinEventsAndSources(events);
    }

    @Override
    public Optional<SourceModel> getSourceById(long id) {
        return sources.stream().filter(source -> source.getId() == id).findFirst();
    }

    @Override
    public List<SourceModel> getSources() {
        return sources;
    }

    @Override
    public synchronized void saveEvents(List<EventModel> events) {
        this.events.addAll(events);
    }

    @Override
    public synchronized void saveSources(List<SourceModel> sources) {
        this.sources.addAll(sources);
    }


    private <T> List<EventWithSourceModel> joinEventsAndSources(List<EventModel> events) {
        List<EventWithSourceModel> eventsWithSource = new ArrayList<EventWithSourceModel>();
        List<SourceModel> sources = getSources();
        for(EventModel event : events) {
            SourceModel foundSource = sources.stream().filter(source -> source.getId() == event.getSourceId()).findAny().orElse(null);
            EventWithSourceModel eventWithSource = new EventWithSourceModel(event.getId(), event.getSourceId(), foundSource, event.getTimestamp(), event.getValue());
            eventsWithSource.add(eventWithSource);
        }
        Collections.sort(eventsWithSource);
        return eventsWithSource;
    }
    
}
