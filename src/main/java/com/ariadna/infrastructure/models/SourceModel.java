package com.ariadna.infrastructure.models;

public class SourceModel {
    long id;
    String name;

    public SourceModel(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{SourceModel: " + id + " " + name + "}";
    }
}
