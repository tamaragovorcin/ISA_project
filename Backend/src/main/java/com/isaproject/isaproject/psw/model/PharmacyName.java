package com.isaproject.isaproject.psw.model;

import javax.persistence.Id;

public class PharmacyName {
    @Id
    private String name;
    private String city;
    private String api;
    public PharmacyName(String name, String city, String api) {
        this.name = name;this.city = city;this.api = api;
    }
    public PharmacyName() {}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getApi() {
        return api;
    }
    public void setApi(String api) {
        this.api = api;
    }
}
