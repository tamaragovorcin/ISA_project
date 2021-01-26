package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Patient;

import java.time.LocalDateTime;

public class MedicationReservationDTO {

    private Patient patient;

    private Pharmacy pharmacy;

    private Medication medication;

    private LocalDateTime dateOfTakeOver;

    public MedicationReservationDTO(Patient patient, Pharmacy pharmacy, Medication medication, LocalDateTime dateOfTakeOver) {
        this.patient = patient;
        this.pharmacy = pharmacy;
        this.medication = medication;
        this.dateOfTakeOver = dateOfTakeOver;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
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


    public LocalDateTime getDateOfTakeOver() {
        return dateOfTakeOver;
    }

    public void setDateOfTakeOver(LocalDateTime dateOfTakeOver) {
        this.dateOfTakeOver = dateOfTakeOver;
    }
}
