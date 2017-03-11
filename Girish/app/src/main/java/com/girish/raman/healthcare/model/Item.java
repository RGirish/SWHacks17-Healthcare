package com.girish.raman.healthcare.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Item {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("choices")
    private List<Choice> choiceList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Choice> getChoiceList() {
        return choiceList;
    }

    public void setChoiceList(List<Choice> choiceList) {
        this.choiceList = choiceList;
    }
}