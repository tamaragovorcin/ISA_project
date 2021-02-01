package com.isaproject.isaproject.Model.Users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;

import javax.persistence.*;

@Entity

public class Mark {

    @Id
    @SequenceGenerator(name = "mySeqGenV2", sequenceName = "mySeqV2", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenV2")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "markOne", nullable = true,unique=false)
    private int markOne;

    @Column(name = "markTwo", nullable = true,unique=false)
    private int markTwo;


    @Column(name = "markThree", nullable = true,unique=false)
    private int markThree;


    @Column(name = "markFour", nullable = true,unique=false)
    private int markFour;


    @Column(name = "markFive", nullable = true,unique=false)
    private int markFive;

    @JsonBackReference(value="pharmacy-mark")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id", referencedColumnName = "id", nullable = true, unique = false)
    private Pharmacy pharmacy;

    @JsonBackReference(value="patient-mark")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = true, unique = false)
    private Patient patient;

    @Column(name = "patientsMark", nullable = true,unique=false)
    private int patientsMark;

    public Mark(Integer id, int markOne, int markTwo, int markThree, int markFour, int markFive, Pharmacy pharmacy, Pharmacist pharmacist, Dermatologist dermatologist, Medication medication) {
        this.id = id;
        this.markOne = markOne;
        this.markTwo = markTwo;
        this.markThree = markThree;
        this.markFour = markFour;
        this.markFive = markFive;
        this.pharmacy = pharmacy;
    }

    public Mark() {
    }

    public int getPatientsMark() {
        return patientsMark;
    }

    public void setPatientsMark(int patientsMark) {
        this.patientsMark = patientsMark;
    }

    public Integer getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public int getMarkOne() {
        return markOne;
    }

    public void setMarkOne(int markOne) {
        this.markOne = markOne;
    }

    public int getMarkTwo() {
        return markTwo;
    }

    public void setMarkTwo(int markTwo) {
        this.markTwo = markTwo;
    }

    public int getMarkThree() {
        return markThree;
    }

    public void setMarkThree(int markThree) {
        this.markThree = markThree;
    }

    public int getMarkFour() {
        return markFour;
    }

    public void setMarkFour(int markFour) {
        this.markFour = markFour;
    }

    public int getMarkFive() {
        return markFive;
    }

    public void setMarkFive(int markFive) {
        this.markFive = markFive;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }


}
