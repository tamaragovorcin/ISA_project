package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Patient;

import java.time.LocalDateTime;

public class MedicationReservationDTO {

    private Patient patient;

    private Pharmacy pharmacy;

    private long medicineCode;

    private LocalDateTime dateOfTakeOver;

    public MedicationReservationDTO(Patient patient, Pharmacy pharmacy, long medicineCode, LocalDateTime dateOfTakeOver) {
        this.patient = patient;
        this.pharmacy = pharmacy;
        this.medicineCode = medicineCode;
        this.dateOfTakeOver = dateOfTakeOver;
    }

    public MedicationReservationDTO() {
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public long getMedicineCode() {
        return medicineCode;
    }

    public void setMedicineCode(long medicineCode) {
        this.medicineCode = medicineCode;
    }

    public LocalDateTime getDateOfTakeOver() {
        return dateOfTakeOver;
    }

    public void setDateOfTakeOver(LocalDateTime dateOfTakeOver) {
        this.dateOfTakeOver = dateOfTakeOver;
    }
}
