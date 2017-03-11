package com.girish.raman.healthcare.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Question {
    @JsonProperty("type")
    private String type;
    @JsonProperty("text")
    private String text;
    @JsonProperty("items")
    private List<Item> itemList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
