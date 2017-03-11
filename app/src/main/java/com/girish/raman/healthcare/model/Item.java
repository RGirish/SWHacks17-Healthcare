package com.girish.raman.healthcare.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Item {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("choices")
    List<Choice> choiceList;
}