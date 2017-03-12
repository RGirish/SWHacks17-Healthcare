package com.swhacks.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Practice {


	private String name;
	@JsonProperty("visit_address")
	private VisitAddress visitAddress;
	@JsonProperty("phones")
	private List<Phone> phones;
	private String website;
	private String distance;
	@JsonProperty("insurance_uids")
	private List<String> insuranceUids;
	@JsonProperty("insuranceData")
	private List<String> insuranceData;

	/**
	 * @return the distance
	 */
	public String getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(String distance) {
		this.distance = distance;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the visitAddress
	 */
	public VisitAddress getVisitAddress() {
		return visitAddress;
	}

	/**
	 * @param visitAddress
	 *            the visitAddress to set
	 */
	public void setVisitAddress(VisitAddress visitAddress) {
		this.visitAddress = visitAddress;
	}

	/**
	 * @return the phones
	 */
	public List<Phone> getPhones() {
		return phones;
	}

	/**
	 * @param phones
	 *            the phones to set
	 */
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website
	 *            the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * @return the insuranceUids
	 */
	public List<String> getInsuranceUids() {
		return insuranceUids;
	}

	/**
	 * @param insuranceUids
	 *            the insuranceUids to set
	 */
	public void setInsuranceUids(List<String> insuranceUids) {
		this.insuranceUids = insuranceUids;
	}

	/**
	 * @return the insuranceData
	 */
	public List<String> getInsuranceData() {
		return insuranceData;
	}

	/**
	 * @param insuranceData
	 *            the insuranceData to set
	 */
	public void setInsuranceData(List<String> insuranceData) {
		this.insuranceData = insuranceData;
	}

}
