package com.isaproject.isaproject.Model.HelpModel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isaproject.isaproject.Model.Medicine.Specification;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.Pharmacist;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Complaint {

    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;


    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false, unique = false)
    private Patient patient;


    @Column(name = "subject", nullable = true)
    private String subject;//na sta se zali


    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "dermatologist_id", referencedColumnName = "id", nullable = true, unique = false)
    private Dermatologist dermatologist;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "pharmacist_id", referencedColumnName = "id", nullable = true, unique = false)
    private Pharmacist pharmacist;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "pharmacy_id", referencedColumnName = "id", nullable = true, unique = false)
    private Pharmacy pharmacy;


    @Column(name = "answered", nullable = true)
    private boolean answered;


    @Column(name = "massage", nullable = true)
    private String massage;


    @Column(name = "answer", nullable = true)
    private String answer;

    public Complaint() {
    }

    public Complaint(Integer id, Patient patient, String subject, Dermatologist dermatologist, Pharmacist pharmacist, Pharmacy pharmacy, boolean answered, String massage, String answer) {
        this.id = id;
        this.patient = patient;
        this.subject = subject;
        this.dermatologist = dermatologist;
        this.pharmacist = pharmacist;
        this.pharmacy = pharmacy;
        this.answered = answered;
        this.massage = massage;
        this.answer = answer;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
