package com.isaproject.isaproject.DTO;

public class PharmacyNameDTO {
    private String pharmacyName;

    public PharmacyNameDTO(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public PharmacyNameDTO() {
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }
}
