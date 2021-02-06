package com.isaproject.isaproject.DTO;

public class PharmacyNameDTO {
    private String pharmacyName;
    private int pharmacyId;

    public PharmacyNameDTO() { }

    public PharmacyNameDTO(String pharmacyName, int pharmacyId) {
        this.pharmacyName = pharmacyName;
        this.pharmacyId = pharmacyId;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }
    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }
}