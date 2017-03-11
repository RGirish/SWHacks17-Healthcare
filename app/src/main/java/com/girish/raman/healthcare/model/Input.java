package com.girish.raman.healthcare.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Input {
    @JsonProperty("sex")
    private String sex;
    @JsonProperty("age")
    private int age;
    @JsonProperty("evidence")
    private List<Evidence> evidenceList;
}
