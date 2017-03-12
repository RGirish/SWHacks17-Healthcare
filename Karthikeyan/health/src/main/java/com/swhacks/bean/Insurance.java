package com.swhacks.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Insurance {

	@JsonProperty("insurance_plan")
	private InsurancePlan insurancePlan;

	/**
	 * @return the insurancePlan
	 */
	public InsurancePlan getInsurancePlan() {
		return insurancePlan;
	}

	/**
	 * @param insurancePlan
	 *            the insurancePlan to set
	 */
	public void setInsurancePlan(InsurancePlan insurancePlan) {
		this.insurancePlan = insurancePlan;
	}

}
