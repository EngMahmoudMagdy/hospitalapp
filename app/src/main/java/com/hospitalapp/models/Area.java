package com.hospitalapp.models;

import java.io.Serializable;

public class Area implements Serializable {
    String name;
    int id;

    public Area() {
    }

    public Area(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
