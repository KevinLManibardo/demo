package com.ariadna.api.models;

public class EventWithSourceResponse {
    long id;
    long sourceId;
    SourceResponse source;
    long timestamp;
    long value;

    public EventWithSourceResponse(long id, long sourceId, SourceResponse source, long timestamp, long value) {
        this.id = id;
        this.sourceId = sourceId;
        this.source = source;
        this.timestamp = timestamp;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public long getSourceId() {
        return sourceId;
    }
    
    public SourceResponse getSource() {
        return source;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getValue(){
        return value;
    }   
}
