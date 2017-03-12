package com.girish.raman.healthcare.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Practice {
    @JsonProperty("name")
    String name;
    @JsonProperty("website")
    String website;
    @JsonProperty("distance")
    String distance;
    @JsonProperty("visit_address")
    Address address;
    @JsonProperty("phones")
    List<Phone> phones;
    @JsonProperty("insurance_uids")
    String insurance_uids;
    @JsonProperty("insuranceData")
    List<String> insuranceData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public String getInsurance_uids() {
        return insurance_uids;
    }

    public void setInsurance_uids(String insurance_uids) {
        this.insurance_uids = insurance_uids;
    }

    public List<String> getInsuranceData() {
        return insuranceData;
    }

    public void setInsuranceData(List<String> insuranceData) {
        this.insuranceData = insuranceData;
    }
}
