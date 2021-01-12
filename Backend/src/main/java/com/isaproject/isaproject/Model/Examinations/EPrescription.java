package com.isaproject.isaproject.Model.Examinations;

import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Users.Patient;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class EPrescription {


    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false, unique = false)
    private Patient patient;


    @Column(name = "code", nullable = false)
    private long code;


    @Column(name = "date", nullable = false)
    private LocalDate date;


    @Column(name = "status", nullable = false)
    private String status;

    @ManyToMany(mappedBy = "ePrescriptions")
    private Set<Medication> medications = new HashSet<Medication>();

    public EPrescription() {

    }

    public EPrescription(Integer id, Patient patient, long code, LocalDate date, String status) {
        this.id = id;
        this.patient = patient;
        this.code = code;
        this.date = date;
        this.status = status;
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

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
