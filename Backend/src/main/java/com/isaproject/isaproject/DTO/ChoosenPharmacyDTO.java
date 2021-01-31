package com.isaproject.isaproject.DTO;

import java.util.List;

public class ChoosenPharmacyDTO {
    private Integer pharmacyId;
    private List<QRcodeInformationDTO> medications;

    public ChoosenPharmacyDTO() {
    }

    public ChoosenPharmacyDTO(Integer pharmacyId, List<QRcodeInformationDTO> medications) {
        this.pharmacyId = pharmacyId;
        this.medications = medications;
    }

    public Integer getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Integer pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public List<QRcodeInformationDTO> getMedications() {
        return medications;
    }

    public void setMedications(List<QRcodeInformationDTO> medications) {
        this.medications = medications;
    }
}
