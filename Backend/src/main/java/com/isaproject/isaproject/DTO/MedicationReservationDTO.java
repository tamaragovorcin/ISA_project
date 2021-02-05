package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Users.Patient;
import java.time.LocalDate;

public class MedicationReservationDTO {

    private Patient patient;

    private int pharmacyId;

    private int medicationId;

    private LocalDate dateOfTakeOver;

    public MedicationReservationDTO() { }

    public MedicationReservationDTO(Patient patient, int pharmacyId, int medicationId, LocalDate dateOfTakeOver) {
        this.patient = patient;
        this.pharmacyId = pharmacyId;
        this.medicationId = medicationId;
        this.dateOfTakeOver = dateOfTakeOver;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public int getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(int medicationId) {
        this.medicationId = medicationId;
    }

    public LocalDate getDateOfTakeOver() {
        return dateOfTakeOver;
    }

    public void setDateOfTakeOver(LocalDate dateOfTakeOver) {
        this.dateOfTakeOver = dateOfTakeOver;
    }
}
