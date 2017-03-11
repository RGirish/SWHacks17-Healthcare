package com.girish.raman.healthcare.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Evidence {
    @JsonProperty("id")
    private String id;
    @JsonProperty("choice_id")
    private String choice_id;
}
