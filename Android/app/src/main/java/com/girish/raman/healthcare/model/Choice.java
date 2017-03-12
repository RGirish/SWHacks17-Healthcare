package com.girish.raman.healthcare.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Choice {
    @JsonProperty("id")
    private String id;
    @JsonProperty("label")
    private String label;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
