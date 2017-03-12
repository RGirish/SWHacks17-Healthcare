package com.girish.raman.healthcare.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties({"extras"})
public class Output {
    @JsonProperty("question")
    private Question questionList;

    @JsonProperty("conditions")
    private List<Condition> conditionList;

    public Question getQuestionList() {
        return questionList;
    }

    public void setQuestionList(Question questionList) {
        this.questionList = questionList;
    }

    public List<Condition> getConditionList() {
        return conditionList;
    }

    public void setConditionList(List<Condition> conditionList) {
        this.conditionList = conditionList;
    }
}
