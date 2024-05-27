package com.ariadna.domain.models;

public class Source {
    long id;
    String name;

    public Source(long id, String name) {
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
