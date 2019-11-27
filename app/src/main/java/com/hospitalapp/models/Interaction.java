package com.hospitalapp.models;

import android.util.Pair;

import java.util.Map;

public class Interaction {
    Pair<String,String> drugs;
    String description;

    public Interaction() {
    }

    public Interaction(Pair<String, String> drugs, String description) {
        this.drugs = drugs;
        this.description = description;
    }

    public Pair<String, String> getDrugs() {
        return drugs;
    }

    public void setDrugs(Pair<String, String> drugs) {
        this.drugs = drugs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
