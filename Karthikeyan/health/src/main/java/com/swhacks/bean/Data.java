package com.swhacks.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

	@JsonProperty("practices")
	private List<Practice> practices;

	/**
	 * @return the practices
	 */
	public List<Practice> getPractices() {
		return practices;
	}

	/**
	 * @param practices
	 *            the practices to set
	 */
	public void setPractices(List<Practice> practices) {
		this.practices = practices;
	}
}
