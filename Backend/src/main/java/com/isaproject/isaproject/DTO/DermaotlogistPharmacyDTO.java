package com.isaproject.isaproject.DTO;

public class DermaotlogistPharmacyDTO {
    private Integer pharmacyId;
    private Integer dermatologistId;

    public DermaotlogistPharmacyDTO() {
    }

    public DermaotlogistPharmacyDTO(Integer pharmacyId, Integer dermatologistId) {
        this.pharmacyId = pharmacyId;
        this.dermatologistId = dermatologistId;
    }

    public Integer getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Integer pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Integer getDermatologistId() {
        return dermatologistId;
    }

    public void setDermatologistId(Integer dermatologistId) {
        this.dermatologistId = dermatologistId;
    }
}