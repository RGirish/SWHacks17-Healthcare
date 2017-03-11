package com.girish.raman.healthcare.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Girish on 3/10/2017.
 */

public class Choice {
    @JsonProperty("id")
    private String id;
    @JsonProperty("label")
    private String label;
}
