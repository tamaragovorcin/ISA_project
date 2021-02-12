package com.isaproject.isaproject.DTO;

public class MedicationForRemovingDTO {
    private Integer pharmacy;
    private Integer medication;

    public MedicationForRemovingDTO() {
    }

    public MedicationForRemovingDTO(Integer pharmacy, Integer medication) {
        this.pharmacy = pharmacy;
        this.medication = medication;
    }

    public Integer getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Integer pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Integer getMedication() {
        return medication;
    }

    public void setMedication(Integer medication) {
        this.medication = medication;
    }
}
