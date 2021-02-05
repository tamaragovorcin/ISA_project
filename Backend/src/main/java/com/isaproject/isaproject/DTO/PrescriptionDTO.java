package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Patient;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PrescriptionDTO {
    private Patient patient;
    private Pharmacy pharmacy;
    private Set<Medication> medications = new HashSet<Medication>();
    private LocalDate date;
    private double durationOfTherapy;
    private Boolean taken;

    public PrescriptionDTO(Patient patient, Pharmacy pharmacy, Set<Medication> medications, LocalDate date, double durationOfTherapy, Boolean taken) {
        this.patient = patient;
        this.pharmacy = pharmacy;
        this.medications = medications;
        this.date = date;
        this.durationOfTherapy = durationOfTherapy;
        this.taken = taken;
    }

    public PrescriptionDTO() { }

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

    public Set<Medication> getMedications() {
        return medications;
    }

    public void setMedications(Set<Medication> medications) {
        this.medications = medications;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getDurationOfTherapy() {
        return durationOfTherapy;
    }

    public void setDurationOfTherapy(double durationOfTherapy) {
        this.durationOfTherapy = durationOfTherapy;
    }

    public Boolean getTaken() {
        return taken;
    }

    public void setTaken(Boolean taken) {
        this.taken = taken;
    }
}