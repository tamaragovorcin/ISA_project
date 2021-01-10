package com.isaproject.isaproject.Model.Examinations;

import com.isaproject.isaproject.Model.Entity;

import java.time.LocalDate;

public class Prescription extends Entity {
    private int patientId;
    private int pharmacyId;
    private LocalDate date;
    private Boolean taken;
    private String information;
    private double durationOfTherapy;

    public Prescription() {
        super();
    }

    public Prescription(int id, int patientId, int pharmacyId, LocalDate date, Boolean taken, String information, double durationOfTherapy) {
        super(id);
        this.patientId = patientId;
        this.pharmacyId = pharmacyId;
        this.date = date;
        this.taken = taken;
        this.information = information;
        this.durationOfTherapy = durationOfTherapy;
    }

    public Prescription(int patientId, int pharmacyId, LocalDate date, Boolean taken, String information, double durationOfTherapy) {
        this.patientId = patientId;
        this.pharmacyId = pharmacyId;
        this.date = date;
        this.taken = taken;
        this.information = information;
        this.durationOfTherapy = durationOfTherapy;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getTaken() {
        return taken;
    }

    public void setTaken(Boolean taken) {
        this.taken = taken;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public double getDurationOfTherapy() {
        return durationOfTherapy;
    }

    public void setDurationOfTherapy(double durationOfTherapy) {
        this.durationOfTherapy = durationOfTherapy;
    }
}
