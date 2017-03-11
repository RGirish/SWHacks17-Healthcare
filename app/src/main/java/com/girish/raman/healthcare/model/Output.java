package com.girish.raman.healthcare.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties({"extras"})
public class Output {
    @JsonProperty("question")
    List<Question> questionList;
    @JsonProperty("conditions")
    List<Condition> conditionList;
}
