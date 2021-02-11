package com.isaproject.isaproject.DTO;
import java.util.List;

public class ChoosenPharmacyDTO {
    private Integer pharmacyId;
    private List<QRcodeInformationDTO> medications;
    private String code;

    public ChoosenPharmacyDTO() {}

    public ChoosenPharmacyDTO(Integer pharmacyId, List<QRcodeInformationDTO> medications, String code) {
        this.pharmacyId = pharmacyId;
        this.medications = medications;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
