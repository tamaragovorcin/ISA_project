package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Users.Patient;
import java.time.LocalDate;

public class MedicationReservationDTO {

    private Integer patient;

    private int pharmacyId;

    private int medicationId;

    private LocalDate dateOfTakeOver;

    public MedicationReservationDTO() { }

    public MedicationReservationDTO(Patient patient, int pharmacyId, int medicationId, LocalDate dateOfTakeOver) {

        this.pharmacyId = pharmacyId;
        this.medicationId = medicationId;
        this.dateOfTakeOver = dateOfTakeOver;
    }


    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
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
