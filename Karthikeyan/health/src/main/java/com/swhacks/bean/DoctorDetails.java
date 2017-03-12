package com.swhacks.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DoctorDetails {

	@JsonProperty("data")
	private List<Data> data;

	/**
	 * @return the data
	 */
	public List<Data> getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(List<Data> data) {
		this.data = data;
	}
}
