package com.ariadna.domain.models;

public class EventWithSource {
    long id;
    long source_id;
    Source source;
    long timestamp;
    long value;

    public EventWithSource(long id, long source_id, Source source, long timestamp, long value) {
        this.id = id;
        this.source_id = source_id;
        this.source = source;
        this.timestamp = timestamp;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public long getSourceId() {
        return source_id;
    }
    
    public Source getSource() {
        return source;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getValue(){
        return value;
    }  
}
