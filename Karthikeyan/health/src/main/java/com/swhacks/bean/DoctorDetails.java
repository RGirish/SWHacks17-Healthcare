package com.swhacks.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DoctorDetails {

	@JsonProperty("data")
	private Data data;

	/**
	 * @return the data
	 */
	public Data getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Data data) {
		this.data = data;
	}
}
