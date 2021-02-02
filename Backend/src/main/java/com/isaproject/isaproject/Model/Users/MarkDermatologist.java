package com.isaproject.isaproject.Model.Users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;

import javax.persistence.*;

@Entity
public class MarkDermatologist {

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

    @JsonBackReference(value="dermatologist-mark")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "dermatologist_id", referencedColumnName = "id", nullable = true, unique = false)
    private Dermatologist dermatologist;


    @JsonBackReference(value="patient-markDermatologist")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = true, unique = false)
    private Patient patient;

    @Column(name = "patientsMark", nullable = true,unique=false)
    private int patientsMark;

    public MarkDermatologist() {
    }

    public Integer getId() {
        return id;
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

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int getPatientsMark() {
        return patientsMark;
    }

    public void setPatientsMark(int patientsMark) {
        this.patientsMark = patientsMark;
    }
}
