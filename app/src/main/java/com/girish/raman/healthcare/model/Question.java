package com.girish.raman.healthcare.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Question {
    @JsonProperty("type")
    private String type;
    @JsonProperty("text")
    private String text;
    @JsonProperty("items")
    List<Item> itemList;
}
