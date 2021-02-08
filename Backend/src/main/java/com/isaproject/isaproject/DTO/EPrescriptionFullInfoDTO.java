package com.isaproject.isaproject.DTO;

import java.util.List;

public class EPrescriptionFullInfoDTO {
    private List<PharmacyMedicationAvailabilityDTO> pharmacies;
    private List<QRcodeInformationDTO> medicationsInQRcode;

    public EPrescriptionFullInfoDTO() {
    }

    public EPrescriptionFullInfoDTO(List<PharmacyMedicationAvailabilityDTO> pharmacies, List<QRcodeInformationDTO> medicationsInQRcode) {
        this.pharmacies = pharmacies;
        this.medicationsInQRcode = medicationsInQRcode;
    }

    public List<PharmacyMedicationAvailabilityDTO> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(List<PharmacyMedicationAvailabilityDTO> pharmacies) {
        this.pharmacies = pharmacies;
    }

    public List<QRcodeInformationDTO> getMedicationsInQRcode() {
        return medicationsInQRcode;
    }

    public void setMedicationsInQRcode(List<QRcodeInformationDTO> medicationsInQRcode) {
        this.medicationsInQRcode = medicationsInQRcode;
    }
}
