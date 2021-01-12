package com.isaproject.isaproject.Model.Examinations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
@Entity
public class Prescription {


    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "patientId", nullable = false)
    private int patientId;


    @Column(name = "pharmacyId", nullable = false)
    private int pharmacyId;


    @Column(name = "date", nullable = false)
    private LocalDate date;


    @Column(name = "taken", nullable = false)
    private Boolean taken;


    @Column(name = "information", nullable = false)
    private String information;


    @Column(name = "durationOfTherapy", nullable = false)
    private double durationOfTherapy;

    public Prescription() {
    }

    public Prescription(Integer id, int patientId, int pharmacyId, LocalDate date, Boolean taken, String information, double durationOfTherapy) {
        this.id = id;
        this.patientId = patientId;
        this.pharmacyId = pharmacyId;
        this.date = date;
        this.taken = taken;
        this.information = information;
        this.durationOfTherapy = durationOfTherapy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
