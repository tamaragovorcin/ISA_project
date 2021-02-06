package com.isaproject.isaproject.DTO;

import java.util.ArrayList;
import java.util.List;

public class DermatologistsFrontDTO {
    private String firstname;

    private String surname;

    private Double markDermatologist;

    private List<String> pharmacies = new ArrayList<>();

    public DermatologistsFrontDTO(String firstname, String surname, Double markDermatologist, List<String> pharmacies) {
        this.firstname = firstname;
        this.surname = surname;
        this.markDermatologist = markDermatologist;
        this.pharmacies = pharmacies;
    }

    public DermatologistsFrontDTO() {
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

    public Double getMarkDermatologist() {
        return markDermatologist;
    }

    public void setMarkDermatologist(Double markDermatologist) {
        this.markDermatologist = markDermatologist;
    }

    public List<String> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(List<String> pharmacies) {
        this.pharmacies = pharmacies;
    }
}
