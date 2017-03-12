package com.girish.raman.healthcare.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
    @JsonProperty("city")
    String city;
    @JsonProperty("state")
    String state;
    @JsonProperty("street")
    String street;
    @JsonProperty("street2")
    String street2;
    @JsonProperty("zip")
    String zip;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return state + " " + street2 + " " + city + " " + state + " " + zip;
    }
}
