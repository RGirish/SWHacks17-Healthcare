package com.girish.raman.healthcare.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Condition {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("probability")
    private double probability;
}
