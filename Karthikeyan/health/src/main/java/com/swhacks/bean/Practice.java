package com.swhacks.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Practice {

	@JsonProperty("location_slug")
	private String locationSlug;
	@JsonProperty("within_search_area")
	private String withinSearchArea;
	@JsonProperty("distance")
	private double distance;
	private String name;
	@JsonProperty("visit_address")
	private VisitAddress visitAddress;
	@JsonProperty("phones")
	private List<Phone> phones;

	/**
	 * @return the locationSlug
	 */
	public String getLocationSlug() {
		return locationSlug;
	}

	/**
	 * @param locationSlug
	 *            the locationSlug to set
	 */
	public void setLocationSlug(String locationSlug) {
		this.locationSlug = locationSlug;
	}

	/**
	 * @return the withinSearchArea
	 */
	public String getWithinSearchArea() {
		return withinSearchArea;
	}

	/**
	 * @param withinSearchArea
	 *            the withinSearchArea to set
	 */
	public void setWithinSearchArea(String withinSearchArea) {
		this.withinSearchArea = withinSearchArea;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(double distance) {
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
}
