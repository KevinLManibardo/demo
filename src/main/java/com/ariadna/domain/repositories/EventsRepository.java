package com.ariadna.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.ariadna.infrastructure.models.EventModel;
import com.ariadna.infrastructure.models.EventWithSourceModel;
import com.ariadna.infrastructure.models.SourceModel;

public interface EventsRepository {
    Optional<EventModel> getEventById(long id);
    List<EventModel> getEventsByTimeStamps(long startDate, long endDate);
    List<EventWithSourceModel> getEventsWithSourceByTimeStamps(long startDate, long endDate);
    List<EventModel> getEventsByValues(long minValue, long maxValue);
    List<EventWithSourceModel> getEventsWithSourceByValues(long minValue, long maxValue);
    List<EventModel> getEventsBySourceId(long source_id);
    List<EventWithSourceModel> getEventsWithSourceBySourceId(long source_id);
    Optional<SourceModel> getSourceById(long id);
    List<SourceModel> getSources(); 
    void saveEvents(List<EventModel> events);
    void saveSources(List<SourceModel> sources);
}
