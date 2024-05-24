package com.ariadna.infrastructure.models;

public class EventWithSourceModel {
    private long id;
    private long sourceId;
    private SourceModel source;
    private long timestamp;
    private long value;

    public EventWithSourceModel(long id, long sourceId, SourceModel source, long timestamp, long value) {
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
    
    public SourceModel getSource() {
        return source;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getValue(){
        return value;
    }

    @Override
    public String toString() {
        return "{EventWithSourceModel: " + sourceId + " " + source + "}";
    }
}
