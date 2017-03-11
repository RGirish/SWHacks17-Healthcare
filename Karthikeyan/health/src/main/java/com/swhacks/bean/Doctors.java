package com.swhacks.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Doctors {

	@JsonProperty("data")
	private List<Data> datum;

	/**
	 * @return the datum
	 */
	public List<Data> getDatum() {
		return datum;
	}

	/**
	 * @param datum
	 *            the datum to set
	 */
	public void setDatum(List<Data> datum) {
		this.datum = datum;
	}
}
