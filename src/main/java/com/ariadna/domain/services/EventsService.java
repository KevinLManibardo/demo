package com.ariadna.domain.services;

import java.util.List;

import com.ariadna.domain.models.EventWithSource;

public interface EventsService {
    List<EventWithSource> getEventsBySource(long sourceId);
    List<EventWithSource> getEventsByTimeStamps(long startDate, long endDate);
    List<EventWithSource> getEventsByValues(long minValue, long maxValue);
}
