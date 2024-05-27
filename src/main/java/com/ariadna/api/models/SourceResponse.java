package com.ariadna.api.models;

public class SourceResponse {
    long id;
    String name;

    public SourceResponse(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
