package com.ariadna.domain.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ariadna.domain.models.EventWithSource;
import com.ariadna.domain.models.Source;
import com.ariadna.domain.repositories.EventsRepository;
import com.ariadna.infrastructure.models.EventWithSourceModel;
import com.ariadna.infrastructure.models.SourceModel;

@Service
public class EventsServiceImpl implements EventsService {
    private final EventsRepository eventsRepository;

    @Autowired
    public EventsServiceImpl(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<EventWithSource> getEventsBySource(long source_id) {
        List<EventWithSourceModel> eventModels = eventsRepository.getEventsWithSourceBySourceId(source_id);
        List<EventWithSource> events = new ArrayList<EventWithSource>();
        for(EventWithSourceModel eventModel : eventModels) {
            events.add(mapModelToEventWithSource(eventModel));
        }
        return events;
  }

    @Override
    public List<EventWithSource> getEventsByTimeStamps(long startDate, long endDate) {
        List<EventWithSourceModel> eventModels = eventsRepository.getEventsWithSourceByTimeStamps(startDate, endDate);
        List<EventWithSource> events = new ArrayList<EventWithSource>();
        for(EventWithSourceModel eventModel : eventModels) {
            events.add(mapModelToEventWithSource(eventModel));
        }
        return events;
    }

    @Override
    public List<EventWithSource> getEventsByValues(long minValue, long maxValue) {
        List<EventWithSourceModel> eventModels = eventsRepository.getEventsWithSourceByValues(minValue, maxValue);
        List<EventWithSource> events = new ArrayList<EventWithSource>();
        for(EventWithSourceModel eventModel : eventModels) {
            events.add(mapModelToEventWithSource(eventModel));
        }
        return events;
    }    

    private EventWithSource mapModelToEventWithSource(EventWithSourceModel eventModel) {
        Source source = mapModelToSource(eventModel.getSource());
        return new EventWithSource(eventModel.getId(), eventModel.getSourceId(), source, eventModel.getTimestamp(), eventModel.getValue());
    }
    private Source mapModelToSource(SourceModel sourceModel) {
        return new Source(sourceModel.getId(), sourceModel.getName());
    }
}
