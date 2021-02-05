package com.isaproject.isaproject.DTO;

public class PharmacistSearchDTO {
    private  String firstName;
    private String surName;

    public PharmacistSearchDTO() {
    }

    public PharmacistSearchDTO(String firstName, String surName) {
        this.firstName = firstName;
        this.surName = surName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }
}
