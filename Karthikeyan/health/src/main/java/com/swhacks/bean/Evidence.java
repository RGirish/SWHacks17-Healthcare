package com.swhacks.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Evidence {

	@JsonProperty("id")
	private String id;
	@JsonProperty("choice_id")
	private String choiceId;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the choiceId
	 */
	public String getChoiceId() {
		return choiceId;
	}

	/**
	 * @param choiceId
	 *            the choiceId to set
	 */
	public void setChoiceId(String choiceId) {
		this.choiceId = choiceId;
	}
}
