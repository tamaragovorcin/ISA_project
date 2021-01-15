package com.isaproject.isaproject.Model.Examinations;

import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.Pharmacist;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Prescription {


    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false, unique = false)
    private Patient patient;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id", referencedColumnName = "id", nullable = false, unique = false)
    private Pharmacy pharmacy;

    @ManyToMany(mappedBy = "prescriptions")
    private Set<Medication> medications = new HashSet<Medication>();

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

    public Prescription(Integer id, Patient patient, Pharmacist pharmacist, LocalDate date, Boolean taken, String information, double durationOfTherapy) {
        this.id = id;
        this.patient = patient;
        this.date = date;
        this.taken = taken;
        this.information = information;
        this.durationOfTherapy = durationOfTherapy;
    }

    public Set<Medication> getMedications() {
        return medications;
    }

    public void setMedications(Set<Medication> medications) {
        this.medications = medications;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
