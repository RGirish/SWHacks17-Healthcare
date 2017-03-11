package com.swhacks.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SecondRequest {

	@JsonProperty("sex")
	private String sex;
	@JsonProperty("age")
	private String age;
	@JsonProperty("evidence")
	private List<Evidence> evidences;

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * @return the evidences
	 */
	public List<Evidence> getEvidences() {
		return evidences;
	}

	/**
	 * @param evidences
	 *            the evidences to set
	 */
	public void setEvidences(List<Evidence> evidences) {
		this.evidences = evidences;
	}

}
