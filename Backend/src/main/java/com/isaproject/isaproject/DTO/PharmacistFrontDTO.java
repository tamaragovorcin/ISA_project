package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;

public class PharmacistFrontDTO {
    private String firstname;

    private String surname;

    private Double markPharmacist;

    private String pharmacy;

    public PharmacistFrontDTO(String firstname, String surname, Double markPharmacist, String pharmacy) {
        this.firstname = firstname;
        this.surname = surname;
        this.markPharmacist = markPharmacist;
        this.pharmacy = pharmacy;
    }

    public PharmacistFrontDTO() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Double getMarkPharmacist() {
        return markPharmacist;
    }

    public void setMarkPharmacist(Double markPharmacist) {
        this.markPharmacist = markPharmacist;
    }

    public String getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(String pharmacy) {
        this.pharmacy = pharmacy;
    }
}
