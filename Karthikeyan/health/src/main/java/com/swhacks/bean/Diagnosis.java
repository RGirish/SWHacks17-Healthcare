package com.swhacks.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Diagnosis {
	@JsonProperty("question")
	private Question question;
	@JsonProperty("conditions")
	private List<Condition> conditions;


	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * @return the conditions
	 */
	public List<Condition> getConditions() {
		return conditions;
	}

	/**
	 * @param conditions
	 *            the conditions to set
	 */
	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}
}
